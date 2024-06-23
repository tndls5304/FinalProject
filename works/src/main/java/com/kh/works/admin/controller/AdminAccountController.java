package com.kh.works.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminAccountController {

//로그인페이지보여주기
    @GetMapping("admin/login")
    public String login() {
        return "login/admin_login";
    }
}
