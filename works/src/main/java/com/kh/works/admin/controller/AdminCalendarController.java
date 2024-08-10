package com.kh.works.admin.controller;

import com.kh.works.admin.servcie.AdminCalendarService;
import com.kh.works.admin.vo.AdminVo;
import com.kh.works.calendar.vo.CalendarVo;
import com.kh.works.calendar.vo.PartnerVo;
import com.kh.works.employee.vo.EmployeeVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminCalendarController {

    private final AdminCalendarService service;

    //달력화면보여주기
    @GetMapping("admin/calendar")
    public String givePage() {
        return "admin/calendar";
    }

    //참여자고를때 부서검색
    // admin/calendar/{deptNo}
    @GetMapping("admin/give/emp-in-dept")
    @ResponseBody
    public ResponseEntity<Object> empList(@RequestParam("deptNo") String deptNo) {
        List<EmployeeVo> empList = service.empList(deptNo);
        /* null이란 힙에 참조할객체가 없는것이고 isEmpty()는 힙메모리에 객체는 있는데 그안에 값이 없는것
        🔸그래서 empList가  null인상태에서 isEmpty() 메서드를 호출하면 NullPointerException 발생하기에 null확인부터 해줌 */
        if (empList == null || empList.isEmpty()) {
            /* 원래는 학원에서 배운게  return ResponseEntity.internalServerError().body("부서검색 결과 조회 실패");
             이거라 그대로 구현했었는데 공부해보니 500번대 서버에러는 서버측에서 예기치못한 문제가 발생했을때 쓰는거더라 */
            return ResponseEntity.noContent().build();
            // 204 No Content:클라이언트는 데이터를 받지 않지만, 요청이 성공적으로 처리되었음(클라이언트가 데이터가 없다는 것을 상태 코드로만 인식하고, 본문이 없어도 처리할 수 있는 경우)
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
        return result == 1
                ? ResponseEntity.ok("스케줄 등록 성공!")
                : ResponseEntity.internalServerError().body("스케줄 등록 실패");

    }

    //캘린더에서 일정 다 불러오기
    @GetMapping("admin/calendar/all")
    @ResponseBody
    public ResponseEntity<List<CalendarVo>> selectScheduleList(HttpSession session) {
        AdminVo loginAdminVo = (AdminVo) session.getAttribute("loginAdminVo");
        String no = loginAdminVo.getNo();
        List<CalendarVo> voList = service.selectScheduleList(no);

        /* 일정 조회하기 같이 클라이언트가 항상 리스트를 기대하는 경우  `voList`가 `null`인 경우 빈 리스트로 대체하여 반환함
         ResponseEntity.ok(voList);: 빈 리스트라도 200 OK 응답을 반환*/
        if (voList == null) {
            voList = Collections.emptyList();         // null일 경우 빈 리스트 반환 (200 OK)
        }
        return ResponseEntity.ok(voList);              // 빈 리스트를 포함하여 200 OK 응답 반환
    }


    //캘린더 상세조회 눌렀을때 캘린더 번호로 참여자들 다 조회해서 데려오기
    @GetMapping("admin/calendar/partner")
    @ResponseBody
    public ResponseEntity<List<PartnerVo>> selectPartnerList(@RequestParam("no") String no) {
        List<PartnerVo> empList = service.selectPartnerList(no);
        if (empList == null || empList.isEmpty()) {
            return ResponseEntity.noContent().build();    // 데이터가 없을 때 204 No Content 상태 코드 반환
        }
        return ResponseEntity.ok(empList);
    }

    //일정 수정하기
    @PostMapping("admin/calendar/update")
    @ResponseBody
    public ResponseEntity<String> updateCalendar(@RequestBody CalendarVo vo, HttpSession session) {
        try {
            // 브라우저에서 JavaScript 객체를 JSON 문자열로 변환해서 보내줬기에 서버에서 JSON 데이터를 받기 위해 @RequestBody를 사용
            AdminVo loginAdminVo = (AdminVo) session.getAttribute("loginAdminVo");
            String authNo = loginAdminVo.getAdminAuthorityNo();
            //권한체크
            if ("2".equals(authNo)) {
                String authYn = service.checkAuthYnForUpdateCalendar();
                if ("N".equals(authYn)) {
                    return  ResponseEntity.status(HttpStatus.FORBIDDEN).body("일정 수정 권한이 없습니다!❌");
                }
            }
            //로그인한 관리자의 번호를 설정
            String no = loginAdminVo.getNo();
            vo.setAdminNo(no);
            // 일정 수정 수행
            int result = service.updateCalendar(vo);
            // 결과에 따라 응답 반환
            return result == 1 ? ResponseEntity.ok("스케줄 수정 성공!") : ResponseEntity.internalServerError().body("스케줄 수정 실패");
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
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("일정 삭제 권한이 없습니다!❌");
                }
            }
            //로그인한 관리자의 번호를 넣어주기
            String adminNo = loginAdminVo.getNo();
            int result = service.deleteCalendar(adminNo, calendarNo);
            return result == 1 ? ResponseEntity.ok("스케줄 삭제 완료!") : ResponseEntity.internalServerError().body("스케줄 삭제 실패");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}//class
