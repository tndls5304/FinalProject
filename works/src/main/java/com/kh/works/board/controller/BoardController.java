package com.kh.works.board.controller;

import com.kh.works.board.service.BoardService;
import com.kh.works.board.vo.BoardVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardController {

    private final BoardService service;

    @GetMapping("wirte")
    public String wirte(){
        return "board/write";
    }

    @PostMapping("write")
    public String write(BoardVo vo){

        int result = service.write(vo);

        if (result != 1){
            return "common/error";
        }
        return "redirect:board/list";
    }


}
