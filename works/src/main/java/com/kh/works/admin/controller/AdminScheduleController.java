package com.kh.works.admin.controller;

import com.kh.works.admin.servcie.AdminAccountService;
import com.kh.works.admin.servcie.AdminScheduleService;
import com.kh.works.employee.vo.EmployeeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AdminScheduleController {

    private final AdminScheduleService service;

    //스케줄화면보여주기
    @GetMapping("admin/manage_schedule")
    public String givePage(){
        return "admin/manage_schedule";
    }


    //참여자고를때 부서검색
    @GetMapping("admin/give/emp-in-dept")
    @ResponseBody
    public ResponseEntity<Object> empList(@RequestParam("deptNo")String deptNo){

        List<EmployeeVo> empList =service.empList(deptNo);
        if(empList==null){
            return ResponseEntity.internalServerError().body("부서검색 결과 조회 실패");
        }

        return ResponseEntity.ok(empList);
    }





}//class
