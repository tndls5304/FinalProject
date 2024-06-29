//-------------------------------수인-----------------------------------

package com.kh.works.employee.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//세션대신에 매개변수로 @AuthenticationPrincipal UserDetails authentication 넣어주세요
@Controller
public class EmpAccountController {
    //가입페이지
    @GetMapping("emp/join")
    public String empJoin(){
        return "join/emp_join";
    }

    
   // 로그인페이지--------------지워야할거
    @GetMapping("emp/login")
    public String emplogin(){
        return "login/emp_login";
    }





}
