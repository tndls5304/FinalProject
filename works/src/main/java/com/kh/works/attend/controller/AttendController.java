package com.kh.works.attend.controller;

import com.kh.works.attend.service.AttendService;
import com.kh.works.attend.vo.AttendVo;
import com.kh.works.employee.vo.EmployeeVo;
import com.oracle.wls.shaded.org.apache.xpath.operations.Mod;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    //나의 근태 리스트에서 달.월로 검색하기
    @GetMapping("search")
    public String searchByDate(@RequestParam("dateSearch") String dateSearch, Model model){
        //list.jsp에서 attendList로 받고 있기 때문에 변경하면 안 된다.
        List<AttendVo> attendList = service.searchByDate(dateSearch);
        model.addAttribute("attendList", attendList);
        return "attend/list";
    }

    //전체 사원 근태관리 리스트 (인사부.DEPT_NO=1인 사원들로 로그인했을 때만 볼 수 있도록)
    @GetMapping("allList")
    public String showAllList(HttpSession session, Model model){

        EmployeeVo loginEmpvo = (EmployeeVo) session.getAttribute("loginEmpVo");

        if(loginEmpvo != null && loginEmpvo.getDeptNo() == "1"){
            List<AttendVo> attenList = service.showAllList();
            model.addAttribute("attendList", attenList);
            return "attend/allList";
        }
        else{
            return "attend/invalidAccess";
        }
    }


}
