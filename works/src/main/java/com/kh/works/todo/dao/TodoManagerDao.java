package com.kh.works.todo.dao;


import com.kh.works.todo.mapper.TodoManagerMapper;
import com.kh.works.todo.vo.TodoManangerVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TodoManagerDao {

    private final TodoManagerMapper mapper;


    public int todoWrite(String todoManagerNo) {
        return mapper.todoWrite(todoManagerNo);
    }
}
