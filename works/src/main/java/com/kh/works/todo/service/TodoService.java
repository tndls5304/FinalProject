package com.kh.works.todo.service;


import com.kh.works.employee.vo.EmployeeVo;
import com.kh.works.todo.dao.TodoDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoDao dao;

    //할일 작성
    public int write() {
        return dao.write();
    }
//
//    public EmployeeVo loginEmp(String no) {
//        return dao.loginEmp(no);
//    }
}
