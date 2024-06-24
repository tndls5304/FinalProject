package com.kh.works.board.controller;

import com.kh.works.board.service.BoardService;
import com.kh.works.board.vo.BoardVo;
import com.kh.works.employee.vo.EmployeeVo;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
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

    @GetMapping("write")
    public String wirte(){
        return "board/main";
    }

    @PostMapping("write")
    public String write(BoardVo vo,HttpSession session){

        EmployeeVo empVo = (EmployeeVo) session.getAttribute("empVo");

        if (empVo == null) { // 세션에서 EmployeeVo를 가져오지 못한 경우 예외 처리
            throw new IllegalStateException("로그인 세션이 만료되었습니다."); // 예외를 던져 처리
        }

        String empNo = empVo.getNo();
        vo.setEmpNo(empNo); // 변수 empNo 앞의 따옴표 제거하여 변수 값으로 설정

        int result = service.write(vo);



        if (result != 1){
            return "common/error";
        }
        return "redirect:board/list";
     }


}
