package com.kh.works.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoDao {
    private final TodoMapper mapper;

    public int write(TodoVo vo, TodoManagerVo mvo) {
        int result1= mapper.write(vo);
        int result2= mapper.todoManager(mvo);
        

        return;
    }


    public int todoManager(TodoManagerVo mvo) {
        return
    }



}
