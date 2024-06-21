
//---------------수인------------------------------------
package com.kh.works.admin.controller;

import com.kh.works.email.entity.EmailMessage;
import com.kh.works.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class AdminController {

@Autowired
   private EmailService service;

@Value("${spring.mail.username}")
    String id;
    @Value("${spring.mail.password}")
    String pw;

    @GetMapping("admin/list_emp")

    public String getEmpList(){
        EmailMessage emailMessage=new EmailMessage();
        emailMessage.setTo("tndls5304@naver.com");
        emailMessage.setMessage("하세요");
        emailMessage.setSubject("안녕");
        service.sendMail(emailMessage);
        System.out.println(id);
        System.out.println(pw);
        return "admin/emp_list";
    }
    //회원등록
    @GetMapping("admin/insert_emp")
    public  String insertEmp(){
        return "admin/insert_emp";
    }
}
