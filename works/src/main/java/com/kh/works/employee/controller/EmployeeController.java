//-------------------------------수인-----------------------------------

package com.kh.works.employee.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {

    @GetMapping("emp/join")
    public String empJoin(){
        return "join/emp_join";
    }

    @GetMapping("emp/login")
    public String emplogin(){
        return "login/emp_login";
    }

    @GetMapping("emp/logout")
    public String emplogout(HttpServletRequest req){
       HttpSession session = req.getSession();
        session.invalidate();
        return "/emp/login";
    }



}
