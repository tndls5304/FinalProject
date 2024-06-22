
//--------------------------수인------------------------------------
package com.kh.works.admin.controller;

import com.kh.works.admin.servcie.AdminService;
import com.kh.works.email.entity.EmailMessage;
import com.kh.works.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class AdminController {

@Autowired
   private final EmailService emailService;
    private  final AdminService adminService;


//회원조회 페이지
    @GetMapping("admin/list_emp")
    public String getEmpList(){

        EmailMessage emailMessage=new EmailMessage();
        emailMessage.setTo("myth5803@naver.com");
        emailMessage.setSubject("다시한번보내용?");
        emailMessage.setMessage("하세요");


        emailService.sendMail(emailMessage);

        return "admin/emp_list";
    }


    //회원등록 페이지
    @GetMapping("admin/insert_emp")
    public  String insertEmp(){

        return "admin/insert_emp";
    }






//테스트
    @GetMapping("test")
public void test(){
        System.out.println(adminService.getTest());
}


}
