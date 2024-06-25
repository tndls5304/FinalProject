package com.kh.works.todo;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoDao dao;


//    @Transactional
//    public int insertTodoAndManager(TodoVo vo, TodoManagerVo mvo){
//        dao.write(vo);
//        dao.todoManager(mvo);
//    }

    public int write(TodoVo vo) {
        return dao.write(vo);
    }

    public int todoManager(TodoManagerVo mvo) {
        return dao.todoManager(mvo);
    }


}
