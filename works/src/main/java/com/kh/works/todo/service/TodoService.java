package com.kh.works.todo.service;


import com.kh.works.employee.vo.EmployeeVo;
import com.kh.works.todo.dao.TodoDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoDao dao;


}
