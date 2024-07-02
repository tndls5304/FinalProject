package com.kh.works.home.mapper;

import com.kh.works.attend.vo.AttendVo;
import com.kh.works.board.vo.BoardVo;
import com.kh.works.employee.vo.EmployeeVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HomeMapper {

    @Insert("INSERT INTO ATTEND ( ATTEND_NO , EMP_NO , START_TIME ) VALUES ( SEQ_ATTEND.NEXTVAL , #{empNo} , SYSDATE )")
    int start(AttendVo vo);

    @Update("UPDATE ATTEND SET END_TIME = CURRENT_TIMESTAMP WHERE EMP_NO = #{empNo}")
    int end(AttendVo vo);

    @Select("SELECT ATTEND_NO ,EMP_NO ,SUBSTR(TO_CHAR(START_TIME, 'RR/MM/DD HH24:MI:SS'), 1, 19) AS START_TIME ,SUBSTR(TO_CHAR(END_TIME, 'RR/MM/DD HH24:MI:SS'), 1, 19) AS END_TIME FROM ATTEND WHERE EMP_NO = #{empNo}")
    AttendVo getAttendInfo(@Param("empNo") String empNo);

    //출근버튼을 찍었는데 퇴근버튼을 찍지 않았으면 출근버튼 다시 찍지 못하도록 막는 메서드
    @Select("SELECT COUNT(*) FROM ATTEND WHERE EMP_NO = #{empNo} AND START_TIME IS NOT NULL AND END_TIME IS NULL AND TO_CHAR(START_TIME, 'YYYY-MM-DD') = TO_CHAR(SYSDATE, 'YYYY-MM-DD')")
    int alreadyStart(String empNo);

    //출퇴근 기록이 있는가
    @Select("SELECT COUNT(*) FROM ATTEND WHERE EMP_NO = #{empNo} AND TO_CHAR(START_TIME, 'YYYY-MM-DD') = TO_CHAR(SYSDATE, 'YYYY-MM-DD') AND END_TIME IS NOT NULL")
    int alreadyAttend(String empNo);





    //*** 수인언니가 작성한 친절한 예시 참고 ***
//    @Select("SELECT E.EMAIL,E.NAME,E.ID,E.PROFILE,E.PHONE,D.NAME,P.NAME FROM EMPLOYEE E JOIN DEPARTMENT D ON E.DEPT_NO=D.NO " +
//            "JOIN POSITION P ON E.POSITION_NO= P.NO WHERE E.NO=#{no}")
//    EmployeeVo selectEmpInfo(String no);
//
//    @Insert("INSERT INTO BOARD\n" +
//            "(\n" +
//            "    BOARD_NO\n" +
//            "    ,EMP_NO\n" +
//            "    ,TITLE\n" +
//            "    ,CONTENT\n" +
//            "    ,VIEW_COUNT\n" +
//            "    ,CRTN_DATE\n" +
//            "    ,MDFD_DATE\n" +
//            "    ,FILE_NAME\n" +
//            "    ,IMG\n" +
//            ")VALUES\n" +
//            "(\n" +
//            "    SEQ_BOARD.NEXTVAL\n" +
//            "    ,#{empNo}\n" +
//            "    ,'${title}'\n" +
//            "    ,'${content}'\n" +
//            "    ,NULL\n" +
//            "    ,SYSTIMESTAMP\n" +
//            "    ,NULL\n" +
//            "    ,NULL\n" +
//            "    ,NULL\n" +
//            "    )")
//    int writing(BoardVo vo);
}
