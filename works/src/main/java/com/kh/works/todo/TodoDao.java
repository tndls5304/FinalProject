package com.kh.works.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TodoDao {
    private final TodoMapper mapper;

    public int write(TodoVo vo) {
        return mapper.write(vo);
    }
}
