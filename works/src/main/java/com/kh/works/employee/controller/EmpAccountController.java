//-------------------------------수인-----------------------------------

package com.kh.works.employee.controller;

import com.kh.works.employee.service.EmpAccountService;
import com.kh.works.employee.vo.EmployeeVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//세션대신에 매개변수로 @AuthenticationPrincipal UserDetails authentication 넣어주세요
@Controller
@RequiredArgsConstructor
public class EmpAccountController {

    private final EmpAccountService service;

    //가입페이지
    @GetMapping("emp/join")
    public String empJoin() {
        return "join/emp_join";
    }


    // 로그인페이지
    @GetMapping("emp/login")
    public String emplogin() {
        return "login/emp_login";
    }

    //로그인하기
    @PostMapping("emp/login")
    public String empLoginMatching(EmployeeVo vo, HttpSession session, Model model) {
        EmployeeVo loginEmpVo = service.empLoginMatching(vo);

        if(loginEmpVo==null){
            model.addAttribute("errorMsg","아이디 비밀번호 확인후 다시 로그인 해주세요!");
           return "login/emp_login";
        }else{
            session.setAttribute("loginEmpVo",loginEmpVo);
            return "redirect:/home";
        }
    }






}
