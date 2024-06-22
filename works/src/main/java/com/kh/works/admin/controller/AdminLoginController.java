package com.kh.works.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminLoginController {

    @GetMapping("admin/login")
    public String login() {
        System.out.println("############## call1");
        return "login/admin_login";
    }


}
