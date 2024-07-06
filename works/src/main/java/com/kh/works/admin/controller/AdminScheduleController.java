package com.kh.works.admin.controller;

import com.kh.works.admin.servcie.AdminAccountService;
import com.kh.works.admin.servcie.AdminScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminScheduleController {

    private final AdminScheduleService service;

    @GetMapping("admin/manage_schedule")
    public String givePage(){
        return "admin/manage_schedule";
    }


}//class
