package com.kh.works.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("board/main")
    public String boardmain(){

        return "board/main";

    }

}
