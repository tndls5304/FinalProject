package com.kh.works.attend.mapper;

import com.kh.works.attend.vo.AttendVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttendMapper {

    //이해가 꼭 필요한 쿼리문
    //1.특정 사원의 출퇴근 기록을 조회하여 2.기록을 월별로 나누고 3.월 내에서 몇 번째 주에 해당하는지 계산하여 4.결과를 정렬해 준다.
    @Select("""
            WITH WEEK_CALCUL AS (
                        SELECT
                            ATTEND_NO,
                            EMP_NO,
                            START_TIME,
                            END_TIME,
                            TO_CHAR(START_TIME, 'MM') AS MONTH_NUM,
                            CEIL(EXTRACT(DAY FROM START_TIME) / 7) AS WEEK_IN_MONTH,
                            ROUND(
                                (TO_DATE(TO_CHAR(END_TIME, 'HH24:MI:SS'), 'HH24:MI:SS') -
                                 TO_DATE(TO_CHAR(START_TIME, 'HH24:MI:SS'), 'HH24:MI:SS')) * 24\s
                            ) AS TOTAL_HOUR,
                            ROUND(
                                (TO_DATE(TO_CHAR(END_TIME, 'HH24:MI:SS'), 'HH24:MI:SS') -
                                 TO_DATE(TO_CHAR(START_TIME, 'HH24:MI:SS'), 'HH24:MI:SS')) * 24 * 60
                            ) AS TOTAL_MINUTES
                        FROM ATTEND
                        WHERE EMP_NO = #{empNo}\s
            )
            SELECT
                ATTEND_NO,
                EMP_NO,
                START_TIME,
                END_TIME,
                MONTH_NUM,
                WEEK_IN_MONTH AS WEEK_NUM,
                FLOOR(TOTAL_HOUR) || '시간 ' || ABS(ROUND((TOTAL_MINUTES - FLOOR(TOTAL_HOUR) * 60))) || '분' AS TOTAL_WORK
            FROM WEEK_CALCUL
            ORDER BY MONTH_NUM, WEEK_NUM, START_TIME
        """)
    @Results({
            @Result(property = "attendNo", column = "ATTEND_NO"),
            @Result(property = "empNo", column = "EMP_NO"),
            @Result(property = "startTime", column = "START_TIME"),
            @Result(property = "endTime", column = "END_TIME"),
            @Result(property = "monthNum", column = "MONTH_NUM"),
            @Result(property = "weekNum", column = "WEEK_NUM"),
            @Result(property = "totalWork", column = "TOTAL_WORK")
    })
    List<AttendVo> myAttendList(String empNo);
    //왜 @Results를 사용하여 매핑하나요?
    // > 데이터베이스(sql)의 칼럼 값과 해당 Vo의 이름이 다를 경우에 @Results를 사용하여 명시적으로 매핑하여 처리해 준다.
    // 근데 굳이 해주지 않아도, 잘 처리된다..


    //위 쿼리문과 동일. 대신 검색하는 기능이기 때문에, WHERE절만 추가
    @Select("""
            WITH WEEK_CALCUL AS (
                        SELECT
                            ATTEND_NO,
                            EMP_NO,
                            START_TIME,
                            END_TIME,
                            TO_CHAR(START_TIME, 'MM') AS MONTH_NUM,
                            CEIL(EXTRACT(DAY FROM START_TIME) / 7) AS WEEK_IN_MONTH,
                            ROUND(
                                (TO_DATE(TO_CHAR(END_TIME, 'HH24:MI:SS'), 'HH24:MI:SS') -
                                 TO_DATE(TO_CHAR(START_TIME, 'HH24:MI:SS'), 'HH24:MI:SS')) * 24\s
                            ) AS TOTAL_HOUR,
                            ROUND(
                                (TO_DATE(TO_CHAR(END_TIME, 'HH24:MI:SS'), 'HH24:MI:SS') -
                                 TO_DATE(TO_CHAR(START_TIME, 'HH24:MI:SS'), 'HH24:MI:SS')) * 24 * 60
                            ) AS TOTAL_MINUTES
                        FROM ATTEND
            )
            SELECT
                ATTEND_NO,
                EMP_NO,
                START_TIME,
                END_TIME,
                MONTH_NUM,
                WEEK_IN_MONTH AS WEEK_NUM,
                FLOOR(TOTAL_HOUR) || '시간 ' || ABS(ROUND((TOTAL_MINUTES - FLOOR(TOTAL_HOUR) * 60))) || '분' AS TOTAL_WORK
            FROM WEEK_CALCUL
            WHERE TO_CHAR(START_TIME, 'MM') = #{dateSearch}
            ORDER BY MONTH_NUM, WEEK_NUM, START_TIME
            """)
    List<AttendVo> searchByDate(@Param("dateSearch") String dateSearch);


    @Select("SELECT E.NAME AS EMPLOYEE_NAME, D.NAME AS DEPARTMENT_NAME, P.NAME AS POSITION_NAME, A.START_TIME, A.END_TIME FROM EMPLOYEE E JOIN ( SELECT EMP_NO, MAX(START_TIME) AS START_TIME FROM ATTEND GROUP BY EMP_NO ) LATEST ON E.NO = LATEST.EMP_NO JOIN ATTEND A ON LATEST.EMP_NO = A.EMP_NO AND LATEST.START_TIME = A.START_TIME JOIN DEPARTMENT D ON E.DEPT_NO = D.NO JOIN POSITION P ON E.POSITION_NO = P.NO")
    @Results({
            @Result(property = "empName", column = "EMPLOYEE_NAME"),
            @Result(property = "deptName", column = "DEPARTMENT_NAME"),
            @Result(property = "positionName", column = "POSITION_NAME")
    })
    List<AttendVo> showAllList();

    @Select("SELECT E.NAME AS empName, D.NAME AS deptName, P.NAME AS positionName, A.START_TIME AS startTime, A.END_TIME AS endTime FROM EMPLOYEE E JOIN ( SELECT EMP_NO, MAX(START_TIME) AS START_TIME FROM ATTEND GROUP BY EMP_NO ) LATEST ON E.NO = LATEST.EMP_NO JOIN ATTEND A ON LATEST.EMP_NO = A.EMP_NO AND LATEST.START_TIME = A.START_TIME JOIN DEPARTMENT D ON E.DEPT_NO = D.NO JOIN POSITION P ON E.POSITION_NO = P.NO WHERE E.NAME LIKE '%' || #{nameSearch} || '%' AND E.DEPT_NO = #{deptSearch}")
    List<AttendVo> searchByNameAndDepartment(@Param("nameSearch") String nameSearch, @Param("deptSearch") String deptSearch);

    @Select("SELECT E.NAME AS empName, D.NAME AS deptName, P.NAME AS positionName, A.START_TIME AS startTime, A.END_TIME AS endTime FROM EMPLOYEE E JOIN ( SELECT EMP_NO, MAX(START_TIME) AS START_TIME FROM ATTEND GROUP BY EMP_NO ) LATEST ON E.NO = LATEST.EMP_NO JOIN ATTEND A ON LATEST.EMP_NO = A.EMP_NO AND LATEST.START_TIME = A.START_TIME JOIN DEPARTMENT D ON E.DEPT_NO = D.NO JOIN POSITION P ON E.POSITION_NO = P.NO WHERE E.NAME LIKE '%' || #{nameSearch} || '%'")
    List<AttendVo> searchByName(@Param("nameSearch") String nameSearch);

    @Select("SELECT E.NAME AS empName, D.NAME AS deptName, P.NAME AS positionName, A.START_TIME AS startTime, A.END_TIME AS endTime FROM EMPLOYEE E JOIN ( SELECT EMP_NO, MAX(START_TIME) AS START_TIME FROM ATTEND GROUP BY EMP_NO ) LATEST ON E.NO = LATEST.EMP_NO JOIN ATTEND A ON LATEST.EMP_NO = A.EMP_NO AND LATEST.START_TIME = A.START_TIME JOIN DEPARTMENT D ON E.DEPT_NO = D.NO JOIN POSITION P ON E.POSITION_NO = P.NO WHERE E.DEPT_NO = #{deptSearch}")
    List<AttendVo> searchByDepartment(@Param("deptSearch") String deptSearch);
}
