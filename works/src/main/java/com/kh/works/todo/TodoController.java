package com.kh.works.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("todo")

public class TodoController {

    private final TodoService service;

//    @GetMapping("home")
//    public String home(){
//
//        return "todo/todoHome";
//    }

    //투두 작성 화면 보여주기
    @GetMapping("write")
    public String write(){
        return "todo/todoWrite";
    }



    //투두 작성
    @PostMapping("write")
    public void write(TodoVo vo){

        int result = service.write(vo);
        System.out.println("todo result = " + result);
    }

    //투두 매니저 선택
    @PostMapping("write")
    public void todoManager(TodoManagerVo mvo){
        int result = service.todoManager(mvo);
        System.out.println("todoManager result = " + result);


    }




}
