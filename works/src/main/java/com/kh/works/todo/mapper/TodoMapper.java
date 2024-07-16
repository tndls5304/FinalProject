package com.kh.works.todo.mapper;

import com.kh.works.alarm.vo.AlarmVo;
import com.kh.works.employee.vo.EmployeeVo;
//import com.kh.works.todo.vo.TodoManangerVo;
import com.kh.works.todo.vo.TodoVo;
import java.util.List;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;



@Mapper
public interface TodoMapper {

    //할일작성
    @Insert("INSERT INTO TODO (TODO_NO, TODO_EMP_NO, TITLE, CONTENT, END_DATE) " +
            "VALUES (SEQ_TODO.NEXTVAL, #{todoEmpNo}, #{title}, #{content}, #{endDate})")
    int todoWrite(TodoVo todoVo);

    //할일 담당자 insert
    @Insert("INSERT INTO TODO_MANAGER(TODO_NO_MAN ,TODO_MANAGER_NO ) VALUES( SEQ_TODO.CURRVAL , #{todoManagerNo})")
    int todoManager(String todoManagerNo);


    //할일 상세조회(담당자조회도 같이
    @Select("""
            SELECT 
            t.TODO_NO,
            e_employee.NAME AS EMP,
            e_employee.NO AS TODO_EMP_NO,  -- 요청자 사원번호
            t.TITLE,
            t.CONTENT,
            t.COMPLETED_YN,
            TO_CHAR(T.CREATE_DATE, 'YYYY-MM-DD') AS CREATE_DATE,
            TO_CHAR(T.END_DATE, 'YYYY-MM-DD') AS END_DATE,
            e_manager.NAME AS MAN,
            e_manager.NO AS TODO_MANAGER_NO  -- 담당자 사원번호
    FROM
    TODO t
    LEFT JOIN
    TODO_MANAGER m ON t.TODO_NO = m.TODO_NO_MAN
    LEFT JOIN
    EMPLOYEE e_employee ON t.TODO_EMP_NO = e_employee.NO
    LEFT JOIN
    EMPLOYEE e_manager ON m.TODO_MANAGER_NO = e_manager.NO
            WHERE
    t.TODO_NO = #{todoNo}
    """)
    @Results({
            @Result(property = "todoEmpName", column = "EMP"),
            @Result(property = "todoEmpNo", column = "TODO_EMP_NO"),
            @Result(property = "todoManagerName", column = "MAN"),
            @Result(property = "todoManagerNo", column = "TODO_MANAGER_NO")
    })

        //@Result : 위의 셀렉트를 실행하고 todoVo에 객체에 매핑해주는 에너테이션
//    @Results({
//            @Result(property = "todoNo", column = "TODO_NO"),
//            @Result(property = "todoEmpNo", column = "TODO_EMP_NO"),
//            @Result(property = "title", column = "TITLE"),
//            @Result(property = "content", column = "CONTENT"),
//            @Result(property = "completedYn", column = "COMPLETED_YN"),
//            @Result(property = "createDate", column = "CREATE_DATE"),
//            @Result(property = "startDate", column = "START_DATE"),
//            @Result(property = "endDate", column = "END_DATE"),
//            //여기서 todoNo를 받아와서
//            // @Many 애너테이션을 통하여 getTodoManager 호출해준다
//            // 그리고 todoVo에 생성해둔 List<String>todoManagerList 에 받아온 리스트를 넣어준다.
//            @Result(property = "todoManagerList", column = "TODO_NO", many = @Many(select = "getTodoManagerList"))
//    })!!!!이러지말고 컨트롤러에서 리스트로 반환 받으라고 이 멍청아.........
    List<TodoVo> getTodoByNo(@RequestParam("todoNo") int todoNo);


    //애초에 컨트롤러에서 todoVo의 반환타입을 List로 해뒀으면 이거 안썼어도 된다......!!!!!!!!!!!!!!!!!whffkWkwmdsksek
//    //할일 담당자 조회
//    //위에서 가져온 todoNo을 가지고 담당자 테이블에서 담당자를 리스트로 가져와 TodoVo에 만들어둔 TodoManagers에 리스트 반환
//    @Select("SELECT TODO_MANAGER_NO, TODO_NO_MAN FROM TODO_MANAGER WHERE TODO_NO_MAN = #{todoNo}")
//    List<String> getTodoManagerList(int todoNo);


    //모든 할일 조회// 이름 가져올때 별칭을 정해줘야 한다.
    @Select("""
            SELECT DISTINCT T.TODO_NO, T.TITLE, COMPLETED_YN, TO_CHAR(T.END_DATE, 'YYYY-MM-DD') AS END_DATE, E.NAME AS NAME
            FROM TODO T
            JOIN TODO_MANAGER M ON T.TODO_NO = M.TODO_NO_MAN
            JOIN EMPLOYEE E ON T.TODO_EMP_NO = E.NO
            WHERE (T.TODO_EMP_NO = #{todoEmpNo} OR M.TODO_MANAGER_NO = #{todoManagerNo})
            AND T.DEL_YN = 'N'
            AND M.DEL_YN = 'N'
            """)
    @Results({
            @Result(property = "todoEmpName", column = "NAME")})
    //내가 만들어둔 vo변수명에 받아온 데이터 들어감
    List<TodoVo> getTodoListAll(TodoVo vo);


    //참여자인 할일 조회
    @Select("""
            SELECT T.TODO_NO, T.TITLE, COMPLETED_YN, E.NAME AS NAME, TO_CHAR(T.END_DATE, 'YYYY-MM-DD') AS END_DATE
            FROM TODO T
            LEFT JOIN TODO_MANAGER M ON T.TODO_NO = M.TODO_NO_MAN
            LEFT JOIN EMPLOYEE E ON T.TODO_EMP_NO = E.NO
            WHERE M.TODO_MANAGER_NO = #{todoManagerNo} AND M.DEL_YN = 'N'
            """)
    @Results({
            @Result(property = "todoEmpName", column = "NAME")})
    //내가 만들어둔 vo변수명에 받아온 데이터 들어감
    List<TodoVo> getTodoListPar(TodoVo vo);


    //할일 수정
    @Update("UPDATE TODO SET TITLE = #{title},CONTENT = #{content} WHERE TODO_NO = #{todoNo}")
    int todoEdit(TodoVo vo);


    //할일 검색 //세션에 로그인 한 직원것만 보이게
    // @Param 애너테이션을 이용해 바인딩
    // 바인딩이란 말 그대로 Java 프로그램에서 SQL 쿼리를 실행하기 위해 사용하는 매개변수를 SQL 쿼리의 실제 컬럼명이나 필드명과 일치시키는 과정
    //@Param 어노테이션은 메서드의 매개변수 이름을 SQL 쿼리에서 사용할 때 사용
    @Select("""
            SELECT T.TODO_NO, T.TITLE, T.CONTENT, E.NAME AS NAME, T.END_DATE
            FROM TODO T
            JOIN EMPLOYEE E ON T.TODO_EMP_NO = E.NO
            LEFT JOIN TODO_MANAGER M ON T.TODO_NO = M.TODO_NO_MAN AND M.DEL_YN = 'N'
            WHERE T.CONTENT LIKE '%' || #{content} || '%'
            AND (T.TODO_EMP_NO = #{todoEmpNo} OR M.TODO_MANAGER_NO = #{todoManagerNo})
            """)
    @Results({
            @Result(property = "todoEmpName", column = "NAME")})
    List<TodoVo> todoSearch(TodoVo vo);

    //할일 삭제
    @Update("UPDATE TODO SET DEL_YN = 'Y' WHERE TODO_NO = #{todoNo}")
    int todoDelete(@RequestParam("todoNo") String todoNo);


    //할일 완료//트리거 이용해서 todo와 todoManager테이블 한번에 업데이트
    @Update("UPDATE TODO SET COMPLETED_YN = 'Y' WHERE TODO_NO = #{todoNo}")
    int todoComplete(@RequestParam("todoNo") String todoNo);


    //참여자 목록 가져오기
    @Select("SELECT E.NO , E.NAME , P.NAME AS positionNO , D.NAME AS deptNO FROM EMPLOYEE E JOIN POSITION P ON E.POSITION_NO = P.NO JOIN DEPARTMENT D ON E.DEPT_NO = D.NO")
    @Results({
            @Result(property = "name", column = "name"),
            @Result(property = "deptNo", column = "deptNo")
    })
    List<EmployeeVo> getManagerList();


    //받은 알람 리스트
    @Select("SELECT ALARM_NO, EMP_NO, MESSAGE, IS_READ, CREATE_DATE FROM ALARM WHERE EMP_NO = #{empNo} AND IS_READ = 'N'")
    List<AlarmVo> getTodoAlarm(@Param("empNo") String empNo);


    //읽은 알람 처리
    @Update("UPDATE ALARM SET IS_READ = 'Y' WHERE EMP_NO = #{empNo} AND IS_READ = 'N'")
    int read(@Param("empNo") String empNo);


    //알람 저장
    @Insert("INSERT INTO ALARM (ALARM_NO, EMP_NO, MESSAGE) VALUES (SEQ_ALARM.NEXTVAL, #{empNo}, #{message})")
    void saveAlarm(@Param("empNo") String empNo, @Param("message") String notificationMessage);


    //사원 상세 조회
    @Select("""
            SELECT\s
                     E.NO AS no,\s
                     E.NAME AS name,\s
                     E.EMAIL AS email,\s
                     P.NAME AS positionName,\s
                     D.NAME AS deptName
            FROM\s
            EMPLOYEE E
            JOIN\s
            POSITION P ON E.POSITION_NO = P.NO
            JOIN\s
            DEPARTMENT D ON E.DEPT_NO = D.NO
            WHERE\s
            E.NO = #{no}
            """)
    @Results({
            @Result(property = "no", column = "no"),
            @Result(property = "name", column = "name"),
            @Result(property = "email", column = "email"),
            @Result(property = "positionName", column = "positionName"),
            @Result(property = "deptName", column = "deptName")
    })
    EmployeeVo getEmpInfo(EmployeeVo empVo);


    //최신작성순 조회
    @Select("""
            SELECT DISTINCT\s
                T.TODO_NO,\s
                T.TITLE,\s
                T.COMPLETED_YN,\s
                TO_CHAR(T.END_DATE, 'YYYY-MM-DD') AS END_DATE,\s
                E.NAME AS NAME,\s
                T.CREATE_DATE AS CREATE_DATE  -- CREATE_DATE에 별칭 추가
            FROM\s
                TODO T
            JOIN\s
                TODO_MANAGER M ON T.TODO_NO = M.TODO_NO_MAN
            JOIN\s
                EMPLOYEE E ON T.TODO_EMP_NO = E.NO
            WHERE\s
                (T.TODO_EMP_NO = #{todoEmpNo} OR M.TODO_MANAGER_NO = #{todoManagerNo})
                AND T.DEL_YN = 'N'
                AND M.DEL_YN = 'N'
            ORDER BY\s
                T.CREATE_DATE DESC
            """)
    @Results({
            @Result(property = "endDate", column = "END_DATE"),
            @Result(property = "createDate", column = "CREATE_DATE"),
            @Result(property = "todoEmpName", column = "NAME")
    })
    List<TodoVo> getTodoListCreateDate(TodoVo vo);


    //기한마감순 조회
    @Select("""
            SELECT DISTINCT\s
                T.TODO_NO,\s
                T.TITLE,\s
                T.COMPLETED_YN,\s
                TO_CHAR(T.END_DATE, 'YYYY-MM-DD') AS END_DATE,\s
                E.NAME AS NAME,\s
                T.CREATE_DATE AS CREATE_DATE,  -- CREATE_DATE에 별칭 추가
                T.END_DATE AS END_DATE\s
            FROM\s
                TODO T
            JOIN\s
                TODO_MANAGER M ON T.TODO_NO = M.TODO_NO_MAN
            JOIN\s
                EMPLOYEE E ON T.TODO_EMP_NO = E.NO
            WHERE\s
                (T.TODO_EMP_NO = #{todoEmpNo} OR M.TODO_MANAGER_NO = #{todoManagerNo})
                AND T.DEL_YN = 'N'
                AND M.DEL_YN = 'N'
            ORDER BY\s
                T.END_DATE ASC
            """)
    @Results({
            @Result(property = "endDate", column = "END_DATE"),
            @Result(property = "createDate", column = "CREATE_DATE"),
            @Result(property = "todoEmpName", column = "NAME")
    })
    List<TodoVo> getTodoListEndDate(TodoVo vo);

    //선택삭제
    @Update("UPDATE TODO SET DEL_YN = 'Y' WHERE TODO_NO = #{todoNo}")
    void selectDelete(Integer todoNo);
}



