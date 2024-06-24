package com.kh.works.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoDao {
    private final TodoMapper mapper;

    public int write(TodoVo vo) {
        return mapper.write(vo);
    }

    public int todoManager(TodoManagerVo mvo) {
        return mapper.todoManager(mvo);
    }



}
