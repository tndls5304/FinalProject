package com.kh.works.todo.dao;

import com.kh.works.employee.vo.EmployeeVo;
import com.kh.works.todo.mapper.TodoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TodoDao {
    private final TodoMapper mapper;

    //로그인 한 회원

//    public EmployeeVo loginEmp(String no) {
//     return mapper.loginEmp(no);
//    }

    //할일 작성
    public int write() {
        return mapper.write();
    }
}
