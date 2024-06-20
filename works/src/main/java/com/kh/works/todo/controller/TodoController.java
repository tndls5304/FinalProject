package com.kh.works.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor

public class TodoController {

    @GetMapping("todo/home")
    public String home(){

        return "todo/todoHome";
    }
}
