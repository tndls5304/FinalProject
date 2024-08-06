package com.kh.works.admin.controller;

import com.kh.works.admin.servcie.AdminCalendarService;
import com.kh.works.admin.vo.AdminVo;
import com.kh.works.calendar.vo.CalendarVo;
import com.kh.works.calendar.vo.PartnerVo;
import com.kh.works.employee.vo.EmployeeVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminCalendarController {

    private final AdminCalendarService service;

    //화면보여주기
    @GetMapping("admin/manage_schedule")
    public String givePage() {
        return "admin/manage_schedule";
    }

    //참여자고를때 부서검색
    // admin/calendar/{deptNo}
    @GetMapping("admin/give/emp-in-dept")
    @ResponseBody
    public ResponseEntity<Object> empList(@RequestParam("deptNo") String deptNo) {
        List<EmployeeVo> empList = service.empList(deptNo);
        if (empList == null) {
            return ResponseEntity.internalServerError().body("부서검색 결과 조회 실패");
        }
        return ResponseEntity.ok(empList);
    }

    //일정등록
    @PostMapping("admin/calendar")
    @ResponseBody
    public ResponseEntity<String> insertSchedule(@RequestBody CalendarVo vo, HttpSession session) {
        // 브라우저에서 JavaScript 객체를 JSON 문자열로 변환해서 보내줬기에 서버에서 JSON 데이터를 받기 위해 @RequestBody를 사용
        AdminVo loginAdminVo = (AdminVo) session.getAttribute("loginAdminVo");
        //권한체크
        String authNo = loginAdminVo.getAdminAuthorityNo();
        if ("2".equals(authNo)) {
            String authYn = service.checkAuthYnForInsertCalendar();
            if ("N".equals(authYn)) {
                return ResponseEntity.internalServerError().body("일정 등록 권한이 없습니다!❌");
            }
        }

        //로그인한 관리자의 번호를 넣어주기
        String no = loginAdminVo.getNo();
        vo.setAdminNo(no);
        int result = service.insertSchedule(vo);
        if (result == 1) {
            return ResponseEntity.ok("스케줄 등록 성공!");
        }
        return ResponseEntity.internalServerError().body("스케줄 등록 실패");
    }

    //캘린더에서 일정 다 불러오기
    @GetMapping("admin/calendar/all")
    @ResponseBody
    public ResponseEntity<List<CalendarVo>> selectScheduleList(HttpSession session) {
        AdminVo loginAdminVo = (AdminVo) session.getAttribute("loginAdminVo");
        String no = loginAdminVo.getNo();
        List<CalendarVo> voList = service.selectScheduleList(no);
        return voList == null ? ResponseEntity.ok().build()
                : ResponseEntity.ok(voList);
    }


    //캘린더 상세조회 눌렀을때 캘린더 번호로 참여자들 다 조회해서 데려오기
    @GetMapping("admin/calendar/partner")
    @ResponseBody
    public ResponseEntity<List<PartnerVo>> selectPartnerList(@RequestParam("no") String no) {
        List<PartnerVo> voList = service.selectPartnerList(no);
        if (voList != null) {
            return ResponseEntity.ok(voList);
        }
        //NULL값 내려주면 오류나니까 성공했다는것만 알아둬... 🐳⭐
        return ResponseEntity.ok().build();
    }

    //일정 수정하기
    @PostMapping("admin/calendar/update")
    @ResponseBody
    public ResponseEntity<String> updateCalendar(@RequestBody CalendarVo vo, HttpSession session) {
        try {
            // 브라우저에서 JavaScript 객체를 JSON 문자열로 변환해서 보내줬기에 서버에서 JSON 데이터를 받기 위해 @RequestBody를 사용
            AdminVo loginAdminVo = (AdminVo) session.getAttribute("loginAdminVo");

            //권한체크
            String authNo = loginAdminVo.getAdminAuthorityNo();
            if ("2".equals(authNo)) {
                String authYn = service.checkAuthYnForUpdateCalendar();
                if ("N".equals(authYn)) {
                    return ResponseEntity.internalServerError().body("일정 수정 권한이 없습니다!❌");
                }
            }
            //로그인한 관리자의 번호를 넣어주기
            String no = loginAdminVo.getNo();
            vo.setAdminNo(no);
            int result = service.updateCalendar(vo);
            if (result == 1) {
                return ResponseEntity.ok("스케줄 수정 성공!");
            }
            return ResponseEntity.internalServerError().body("스케줄 수정 실패");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    //일정삭제
    @PostMapping("admin/calendar/delete")
    @ResponseBody
    public ResponseEntity<String> deleteCalendar(@RequestParam("calendarNo") String calendarNo, HttpSession session) {
        try {
            AdminVo loginAdminVo = (AdminVo) session.getAttribute("loginAdminVo");
            //권한체크
            String authNo = loginAdminVo.getAdminAuthorityNo();
            if ("2".equals(authNo)) {
                String authYn = service.checkAuthYnForDeleteCalendar();
                if ("N".equals(authYn)) {
                    return ResponseEntity.internalServerError().body("일정 삭제 권한이 없습니다!❌");
                }
            }
            //로그인한 관리자의 번호를 넣어주기
            String adminNo = loginAdminVo.getNo();
            int result = service.deleteCalendar(adminNo, calendarNo);
            if (result == 1) {
                return ResponseEntity.ok("스케줄삭제 완료!");
            }
            return ResponseEntity.internalServerError().body("스케줄삭제 실패");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }
}//class
