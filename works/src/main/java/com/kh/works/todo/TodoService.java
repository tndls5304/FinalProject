package com.kh.works.todo;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoDao dao;


    public int write(TodoVo vo) {
        return dao.write(vo);
    }
}
