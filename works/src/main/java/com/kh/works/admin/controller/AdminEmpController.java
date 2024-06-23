
//--------------------------수인------------------------------------
package com.kh.works.admin.controller;

import com.kh.works.admin.servcie.AdminEmpService;
import com.kh.works.admin.email.entity.EmailMessage;
import com.kh.works.admin.email.service.EmailService;
import com.kh.works.admin.vo.DeptVo;
import com.kh.works.admin.vo.EmployeeVo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminEmpController {

    private static final Logger log = LoggerFactory.getLogger(AdminEmpController.class);
    @Autowired
   private final EmailService emailService;
private final AdminEmpService adminEmpService;


    // 신규 사원 등록 페이지 로드
    @GetMapping("admin/insert_emp")
    public String insertEmp(){
        return "admin/insert_emp";
    }

    //신규사원 등록하는 페이지: 부서 조회해오기
    @GetMapping("admin/select_dept")
    @ResponseBody
    public List<DeptVo> selectDeptList(){
        List<DeptVo>voList =adminEmpService.selectDeptList();
        System.out.println("컨트롤러야 부서리스트가져옴?"+voList);
        return  voList;
    }


    //신규직원등록페이지에서  신규 직원 등록 버튼누르면 동작!
    @PostMapping("admin/insert_emp")
    public void insertEmp(EmployeeVo employeeVo){
        adminEmpService.insertEmp(employeeVo);

        //이메일보내기
        EmailMessage emailMessage=new EmailMessage();
        emailMessage.setTo("myth5803@naver.com");
        emailMessage.setSubject("다시한번보내용?");
        emailMessage.setMessage("하세요");


        emailService.sendMail(emailMessage);
        return ;
    }



    //회원조회 페이지
    @GetMapping("admin/list_emp")
    public String getEmpList(){
        return "admin/emp_list";
    }






    //테스트
    @GetMapping("test")
public void test(){
        System.out.println(adminEmpService.getTest());
}


}
