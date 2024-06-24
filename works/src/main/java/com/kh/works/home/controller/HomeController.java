package com.kh.works.home.controller;

import com.kh.works.employee.vo.EmpSessionVo;
import com.kh.works.employee.vo.EmployeeVo;
import com.kh.works.home.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final HomeService service;


    //홈화면 보여주는곳
    @GetMapping("home")
    public String getHomePage (){
        return "home/home";
    }

    //세션안의 인증객체에서 no를 받아와서 -> 홈화면에 보내주기
    @GetMapping("emp/info")
    @ResponseBody
    public EmployeeVo selectEmpInfo(@AuthenticationPrincipal EmpSessionVo loginEmployeeVo){

        String no=loginEmployeeVo.getNo();

        return  service.selectEmpInfo(no);
    }

}
