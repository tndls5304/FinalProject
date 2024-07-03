package com.kh.works.attend.controller;

import com.kh.works.attend.service.AttendService;
import com.kh.works.attend.vo.AttendVo;
import com.kh.works.employee.vo.EmployeeVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("attend")
public class AttendController {

    private final AttendService service;

    //나의 근태 리스트 (내 출퇴근 기록 보기)
    @GetMapping("list")
    public String myAttendList(HttpSession session, Model model){

        EmployeeVo loginEmpVo = (EmployeeVo) session.getAttribute("loginEmpVo");

        String empNo = loginEmpVo.getNo();

        List<AttendVo> attendList = service.myAttendList(empNo);
        model.addAttribute("attendList", attendList);
        return "attend/list";
    }

}
