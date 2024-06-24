package com.kh.works.todo;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TodoMapper {


    @Insert("INSERT INTO TODO ( TODO_NO, TODO_EMP_NO, TITLE, CONTENT, CREATE_DATE, START_DATE, END_DATE ) VALUES (SEQ_TODO.NEXTVAL, #{empNo}, #{title}, #{content}, SYSDATE, SYSDATE, SYSDATE+1)")
    public int write(TodoVo vo);

}
