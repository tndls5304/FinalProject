package com.kh.works.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("todo")

public class TodoController {

    private final TodoService service;

    @GetMapping("home")
    public String home(){

        return "todo/todoHome";
    }

    //투두 작성 화면 보여주기

    //투두 작성
    @PostMapping("write")
    public String write(TodoVo vo){

        int result = service.write(vo);

        return "todo/todoHome";
    }
}
