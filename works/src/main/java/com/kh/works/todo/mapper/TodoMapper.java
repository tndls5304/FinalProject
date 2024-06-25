package com.kh.works.todo.mapper;

import com.kh.works.employee.vo.EmployeeVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TodoMapper {

    //로그인 한 직원 가져오기
//    @Select()
//    EmployeeVo loginEmp(String no);

//    @Insert("INSERT INTO TODO ( TODO_NO, TODO_EMP_NO, TITLE, CONTENT, CREATE_DATE, START_DATE, END_DATE ) VALUES ( SEQ_TODO.NEXTVAL, 1, '할일1', '할일 내용 1', SYSDATE, SYSDATE, SYSDATE + 7)");
    public int write();
}
