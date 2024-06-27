package com.kh.works.todo.mapper;


import com.kh.works.todo.vo.TodoManangerVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TodoManagerMapper {

    @Insert("INSERT INTO TODO_MANAGER (TODO_MANAGER_NO, TODO_NO_MAN)VALUES (8, SEQ_TODO.CURRVAL)")
    int todoWrite(TodoManangerVo manVo);
}
