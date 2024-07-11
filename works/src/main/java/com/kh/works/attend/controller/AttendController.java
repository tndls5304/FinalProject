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

        if (loginEmpvo != null) {
            //DEPT_NO가 1인 사원임을 확인하기 위한 구현
            String deptNo = String.valueOf(loginEmpvo.getDeptNo());
            System.out.println("Department No: " + deptNo);

            //EmployeeVo를 model에 추가할 수 있다. -> JSP에서 사용하기 위해서.
            //loginEmpVo 객체를 employee 이름으로 모델에 추가하게 된다. -> JSP로 데이터를 전달.
            //그런데, JSP에서 ${employee.x}로 작성할 경우, 현재 로그인한 사원의 정보로 나온다는 점.
            model.addAttribute("employee", loginEmpvo);

            if (deptNo.equals("1")) {
                List<AttendVo> attenList = service.showAllList();
                model.addAttribute("attendList", attenList);
                return "attend/allList";
            }
        }
        return "attend/invalidAccess";
    }

    //검색하기 기능. 전체 근태 리스트에서 부서명, 이름으로 검색하기 -> 인사부에서만 확인 가능. (부서명 || 이름 || 부서명 && 이름)
    @GetMapping("searchFromAll")
    public String search(@RequestParam("deptSearch") String deptSearch, @RequestParam("nameSearch") String nameSearch, Model model){

        List<AttendVo> attendList = service.search(deptSearch, nameSearch);
        model.addAttribute("attendList", attendList);
        return "attend/allList";
    }

}
