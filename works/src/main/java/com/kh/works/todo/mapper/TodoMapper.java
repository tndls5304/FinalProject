package com.kh.works.todo.mapper;

import com.kh.works.employee.vo.EmployeeVo;
import com.kh.works.todo.vo.TodoVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


//투두 생성
@Mapper
public interface TodoMapper {

    @Insert("INSERT INTO TODO (TODO_NO, TODO_EMP_NO, TITLE, CONTENT) VALUES (SEQ_TODO.NEXTVAL, 7, #{title}, #{content})")
    int todoWrite(TodoVo todoVo);

    //로그인 한 직원 가져오기
//    @Select()
//    EmployeeVo loginEmp(String no);

}
