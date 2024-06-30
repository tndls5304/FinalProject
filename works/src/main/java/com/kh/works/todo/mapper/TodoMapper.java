package com.kh.works.todo.mapper;

import com.kh.works.todo.vo.TodoManangerVo;
import com.kh.works.todo.vo.TodoVo;
import java.util.List;
import org.apache.ibatis.annotations.*;


//할일 작성
@Mapper
public interface TodoMapper {

    @Insert("INSERT INTO TODO (TODO_NO, TODO_EMP_NO, TITLE, CONTENT, END_DATE) VALUES (SEQ_TODO.NEXTVAL, #{todoEmpNo}, #{title}, #{content}, #{endDate})")
    //insert가 실행되고 나서 자동으로 시퀀스 값을 가져와 todoNo필드에 할당하도록 도와주는 코드
    @SelectKey(statement = "SELECT SEQ_TODO.CURRVAL FROM DUAL", keyProperty = "todoNo", before = false, resultType = Integer.class)
    int todoWrite(TodoVo todoVo);



    //할일 상세조회(담당자조회도 같이
    @Select("SELECT T.TODO_NO, T.TODO_EMP_NO, T.TITLE, T.CONTENT, T.COMPLETED_YN, T.CREATE_DATE, T.END_DATE FROM TODO T WHERE T.TODO_NO = #{no}")
    //@Result : 위의 셀렉트를 실행하고 todoVo에 객체에 매핑해주는 에너테이션
    @Results({
            @Result(property = "todoNo", column = "TODO_NO"),
            @Result(property = "todoEmpNo", column = "TODO_EMP_NO"),
            @Result(property = "title", column = "TITLE"),
            @Result(property = "content", column = "CONTENT"),
            @Result(property = "completedYn", column = "COMPLETED_YN"),
            @Result(property = "createDate", column = "CREATE_DATE"),
            @Result(property = "startDate", column = "START_DATE"),
            @Result(property = "endDate", column = "END_DATE"),
            //여기서 todoNo를 받아와서
            // @Many 애너테이션을 통하여 getTodoManager 호출해준다
            // 그리고 todoVo에 생성해둔 List<todoManagers>에 받아온 리스트를 넣어준다.
            @Result(property = "todoManagers", column = "TODO_NO", many = @Many(select = "getTodoManagerNo"))
    })
    TodoVo getTodoByNo(String no);

    //할일 담당자 조회
    //위에서 가져온 todoNo을 가지고 담당자 테이블에서 담당자를 리스트로 가져와 TodoVo에 만들어둔 TodoManagers에 리스트 반환
    @Select("SELECT TODO_NO_MAN, TODO_MANAGER_NO FROM TODO_MANAGER WHERE TODO_NO_MAN = #{todoNo}")
    List<TodoManangerVo> getTodoManagerNo(String todoNo);



    //모든 할일 조회
    @Select("SELECT DISTINCT T.TITLE, T.END_DATE, E.NAME, T.CREATE_DATE\n" +
            "FROM TODO T\n" +
            "JOIN TODO_MANAGER M ON T.TODO_NO = M.TODO_NO_MAN\n" +
            "JOIN EMPLOYEE E ON T.TODO_EMP_NO = E.NO\n" +
            "WHERE (T.TODO_EMP_NO = #{empNo} OR M.TODO_MANAGER_NO = #{empNo})\n" +
            "AND T.DEL_YN = 'N'\n" +
            "AND M.DEL_YN = 'N'")
    List<TodoVo> getTodoListAll(String empNo);


    //참여자인 할일 조회
    @Select("SELECT T.TITLE, E.NAME AS 담당자, T.END_DATE\n" +
            "FROM TODO T\n" +
            "LEFT JOIN TODO_MANAGER M ON T.TODO_NO = M.TODO_NO_MAN\n" +
            "LEFT JOIN EMPLOYEE e ON T.TODO_EMP_NO = E.NO\n" +
            "WHERE M.TODO_MANAGER_NO = #{empNo}\n" +
            "AND M.DEL_YN = 'N'")
    List<TodoVo> getTodoListPar(String empNo);


    //할일 수정
//    @Update("UPDATE TODO\n" +
//            "SET TITLE = #{title},CONTENT = #{content}\n" +
//            "WHERE TODO_NO = #{todoNo}")
//    int todoEdit(TodoVo vo, String todoNo);
}
