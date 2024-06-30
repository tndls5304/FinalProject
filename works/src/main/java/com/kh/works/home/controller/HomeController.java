package com.kh.works.home.controller;

import com.kh.works.board.vo.BoardVo;
import com.kh.works.employee.vo.EmployeeVo;
import com.kh.works.home.service.HomeService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final HomeService service;

    //그냥 홈화면 보여주는곳
    @GetMapping("home")
    public String getHomePage() {

        return "home/home";
    }


    //내정보 보여주기
    @GetMapping("emp/info")
    @ResponseBody

    public EmployeeVo selectEmpInfo(HttpSession session) {

        EmployeeVo loginEmpVo = (EmployeeVo) session.getAttribute("loginEmpVo");

        String no = loginEmpVo.getNo();
        String name = loginEmpVo.getName();

        System.out.println("name?은?" + name);
        return service.selectEmpInfo(no);      //직원번호에 해당하는 이름,아이디를 가져옴
    }


    //게시판에 글적기 샘플용
    @PostMapping("writing")

    public String writing(HttpSession session, BoardVo boardVo) {
        EmployeeVo loginEmpVo = (EmployeeVo) session.getAttribute("loginEmpVo");

        String no = loginEmpVo.getNo();

        boardVo.setEmpNo(no);
        int result = service.writing(boardVo);
        if (result != 1) {
            return "common/error";
        }
        return "redirect:home/home";

    }
}



