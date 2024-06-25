package com.kh.works.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class AdminHomeController {



    //단순히 어드민 화면 보여주기

    @GetMapping("admin/home")
    public String showHomePage(){
        return "admin/admin_home";
    }






}
