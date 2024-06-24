package com.kh.works.todo;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TodoMapper {


    @Insert("INSERT INTO TODO ( TODO_NO, TODO_EMP_NO, TITLE, CONTENT, CREATE_DATE, START_DATE, END_DATE ) VALUES (SEQ_TODO.NEXTVAL, 1, #{title}, #{content}, SYSDATE, SYSDATE, SYSDATE+1)")
    public int write(TodoVo vo);


    @Insert("INSERT INTO TODO_MANAGER ( TODO_MANAGER_NO, TODO_NO ) VALUES (2,  SEQ_TODO.CURRVAL)")
    int todoManager(TodoManagerVo mvo);


}
