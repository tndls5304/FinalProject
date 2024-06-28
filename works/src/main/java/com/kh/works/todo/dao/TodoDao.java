package com.kh.works.todo.dao;

import com.kh.works.employee.vo.EmployeeVo;
import com.kh.works.todo.mapper.TodoMapper;
import com.kh.works.todo.vo.TodoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TodoDao {
    private final TodoMapper mapper;

    public int todowrite(TodoVo todoVo) {
        return mapper.todoWrite(todoVo);
    }


}
