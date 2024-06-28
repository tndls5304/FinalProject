package com.kh.works.todo.controller;

import com.kh.works.employee.vo.EmployeeVo;
import com.kh.works.security.EmpSessionVo;
import com.kh.works.todo.service.TodoService;
import com.kh.works.todo.vo.TodoAllVo;
import com.kh.works.todo.vo.TodoVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
@RequestMapping("todo")
public class TodoController {


    private final TodoService service;


    //할일 작성
    @PostMapping("write")
    public int todoWrite(TodoAllVo allVo ){

//        //내가 가지고 올 칼럼명, 사원명하고 이름 가져옴
//        String todoEmpNo = loginEmployeeVo.getNo();
//        String todoEmpName = loginEmployeeVo.getUsername();
//        System.out.println("todoEmpNo = " + todoEmpNo);
//        System.out.println("todoEmpName = " + todoEmpName);
//        //할일 등록할때 사원번호와 이름이 필요하니까 세션에서 가져오기
//        allVo.setTodoEmpNo(todoEmpNo);
//        allVo.setTodoEmpName(todoEmpName);


        System.out.println("allVo = " + allVo);
        int result = service.todoWrite(allVo);
        System.out.println("result = " + result);
                
        return result;
    }

}
