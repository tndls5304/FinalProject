package com.kh.works.todo.controller;

import com.kh.works.employee.vo.EmployeeVo;
import com.kh.works.security.EmpSessionVo;
import com.kh.works.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("todo")
public class TodoController {


    private final TodoService service;

    //로그인한 회원정보 가져오기(보안 강화된 세션..?
//    public EmployeeVo selectEmp(@AuthenticationPrincipal EmpSessionVo loginEmpVo){
//        String no = loginEmpVo.getNo();
//        return service.loginEmp(no);
//    }


    //작성 화면 보여주기
    @GetMapping("write")
    public String todoWrite(){

        return "todo/todoWrite";
    }

    //할일 작성
    @PostMapping("write")
    public void Write(){
        int result = service.write();
    }


}
