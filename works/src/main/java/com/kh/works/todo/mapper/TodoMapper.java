package com.kh.works.todo.mapper;

import com.kh.works.employee.vo.EmployeeVo;
import com.kh.works.todo.vo.TodoManangerVo;
import com.kh.works.todo.vo.TodoVo;
import java.util.List;
import org.apache.ibatis.annotations.*;


//투두 생성
@Mapper
public interface TodoMapper {

    @Insert("INSERT INTO TODO (TODO_NO, TODO_EMP_NO, TITLE, CONTENT, END_DATE) VALUES (SEQ_TODO.NEXTVAL, #{todoEmpNo}, #{title}, #{content}, #{endDate})")
    //insert가 실행되고 나서 자동으로 시퀀스 값을 가져와 todoNo필드에 할당하도록 도와주는 코드
    @SelectKey(statement = "SELECT SEQ_TODO.CURRVAL FROM DUAL", keyProperty = "todoNo", before = false, resultType = Integer.class)
    int todoWrite(TodoVo todoVo);

    //투두 상세조회
    @Select("SELECT t.TODO_NO, t.TODO_EMP_NO, t.TITLE, t.CONTENT, t.COMPLETED_YN, " +
            "t.CREATE_DATE, t.END_DATE, t.DEL_YN " +
            "FROM TODO t " +
            "WHERE t.TODO_NO = #{no}")
    @Results({
            @Result(property = "todoNo", column = "TODO_NO"),
            @Result(property = "todoEmpNo", column = "TODO_EMP_NO"),
            @Result(property = "title", column = "TITLE"),
            @Result(property = "content", column = "CONTENT"),
            @Result(property = "completedYn", column = "COMPLETED_YN"),
            @Result(property = "createDate", column = "CREATE_DATE"),
            @Result(property = "startDate", column = "START_DATE"),
            @Result(property = "endDate", column = "END_DATE"),
            @Result(property = "delYn", column = "DEL_YN"),
            @Result(property = "managers", column = "TODO_NO", many = @Many(select = "selectManagersByTodoNo"))
    })
    TodoVo getTodoByNo(String no);

    @Select("SELECT TODO_NO_MAN, TODO_MANAGER_NO FROM TODO_MANAGER WHERE TODO_NO_MAN = #{todoNo}")
    List<TodoManangerVo> selectManagersByTodoNo(String todoNo);
}
