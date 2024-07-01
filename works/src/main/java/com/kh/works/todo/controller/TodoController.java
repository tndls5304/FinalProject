package com.kh.works.todo.controller;

//주석처리 했어용
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import com.kh.works.security.EmpSessionVo;
import com.kh.works.employee.vo.EmployeeVo;
import com.kh.works.todo.service.TodoService;
import com.kh.works.todo.vo.TodoAllVo;
import com.kh.works.todo.vo.TodoVo;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("todo")
public class TodoController {


    private final TodoService service;



    //할일 작성 화면
    @GetMapping("write")
    public String write(){
        return "todo/write";
    }


    //할일 상세 조회 화면
    @GetMapping("detail")
    public String detail(){
        return "todo/detail";
    }


    //모든 할일 목록조회 화면(담당자 참여자 모두
    @GetMapping("listAll")
    public String listAll(){
        return "todo/listAll";
    }


    //참여자 할일 목록조회 화면(내가 참여자인것만
    @GetMapping("listPar")
   public String listPar() {
        return "todo/listPar";
    }


    //할일 수정 화면
    @GetMapping("edit")
    public String edit(){
        return "todo/edit";

    }


    //할일 검색

    //할일 삭제...

}
