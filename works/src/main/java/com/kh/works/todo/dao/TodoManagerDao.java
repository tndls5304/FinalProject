package com.kh.works.todo.dao;


import com.kh.works.todo.mapper.TodoManagerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TodoManagerDao {

    private final TodoManagerMapper mapper;


}
