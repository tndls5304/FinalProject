package com.kh.works.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class AdminController {

    @GetMapping("admin/list_emp")
    public String getEmpList(){
        return "admin/emp_list";
    }
    //회원등록
    @GetMapping("admin/insert_emp")
    public  String insertEmp(){
        return "admin/insert_emp";
    }
}
