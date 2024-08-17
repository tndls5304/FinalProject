package com.kh.works.admin.controller;

import com.kh.works.admin.servcie.AdminCalendarService;
import com.kh.works.admin.vo.AdminVo;
import com.kh.works.aop.annotation.AuthCheckAop;
import com.kh.works.calendar.vo.CalendarVo;
import com.kh.works.calendar.vo.PartnerVo;
import com.kh.works.employee.vo.EmployeeVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 달력을 이용해 관리자의 일정관리
 *
 * @author 이수인
 * @since 2024. 07. 17
 */
@Controller
@RequiredArgsConstructor
public class AdminCalendarController {

    private final AdminCalendarService service;

    /**
     * 달력 화면 제공
     *
     * @return 이동할 URL
     */
    @GetMapping("admin/calendar")
    public String givePage() {
        return "admin/calendar";
    }

    /**
     * 파라미터로 넘어온 부서에 해당하는 사원들 조회 요청
     *
     * @param deptNo 부서번호
     * @return 조회한 데이터가 없을경우 요청은 정상이나 반환될 데이터가 없다는 204 No Content를 알리고 조회 결과가 있을경우 반환함
     */
    //참여자고를때 부서검색
    // admin/calendar/{deptNo}
    @GetMapping("admin/give/emp-in-dept")
    @ResponseBody
    public ResponseEntity<Object> empList(@RequestParam("deptNo") String deptNo) {
        List<EmployeeVo> empList = service.empList(deptNo);
        /* null이란 힙에 참조할객체가 없는것이고 isEmpty()는 힙메모리에 객체는 있는데 그안에 값이 없는것
        그래서 empList가  null인상태에서 isEmpty() 메서드를 호출하면 NullPointerException 발생하기에 null확인부터 해줌 */
        if (empList == null || empList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(empList);

    }

    /**
     * 일정등록 요청
     *
     * @param vo      제목,내용,날짜,장소 등 일정 정보
     * @param session 작성자 번호를 알기 위함
     * @return 등록 성공 여부에 따라 상태코드,메세지 응답
     */
    @AuthCheckAop("INSERT_CALENDAR")
    @PostMapping("admin/calendar")
    @ResponseBody
    public ResponseEntity<String> insertSchedule(@RequestBody CalendarVo vo, HttpSession session) {
        AdminVo loginAdminVo = (AdminVo) session.getAttribute("loginAdminVo");
        String no = loginAdminVo.getNo();
        vo.setAdminNo(no);
        int result = service.insertSchedule(vo);
        return result == 1
                ? ResponseEntity.ok("스케줄 등록 성공!")
                : ResponseEntity.internalServerError().body("스케줄 등록 실패");
    }

    /**
     * 캘린더 일정 전체 조회 요청
     *
     * @param session 작성자 번호를 알기 위함
     * @return 조회한 데이터가 없을시 통신은 성공적이나 데이터가 없다는 204 No Content응답. 조회결과 있으면 반환
     */
    @GetMapping("admin/calendar/all")
    @ResponseBody
    public ResponseEntity<List<CalendarVo>> selectScheduleList(HttpSession session) {
        AdminVo loginAdminVo = (AdminVo) session.getAttribute("loginAdminVo");
        String no = loginAdminVo.getNo();
        List<CalendarVo> voList = service.selectScheduleList(no);
        if (voList == null || voList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(voList);
    }

    /**
     * 캘린더 상세 조회시 해당 일정을 공유하는 참여자들 조회 요청
     *
     * @param no 캘린더번호
     * @return 반환할 데이터가 없을시  204 No Content응답. 조회 결과 있으면 참여자 리스트 반환
     */
    @GetMapping("admin/calendar/partner")
    @ResponseBody
    public ResponseEntity<List<PartnerVo>> selectPartnerList(@RequestParam("no") String no) {
        List<PartnerVo> empList = service.selectPartnerList(no);
        if (empList == null || empList.isEmpty()) {
            return ResponseEntity.noContent().build();    // 데이터가 없을 때 204 No Content 상태 코드 반환
        }
        return ResponseEntity.ok(empList);
    }

    /**
     * 캘린더 일정 수정
     *
     * @param vo      수정할 데이터
     * @param session 작성자 번호 알기위함
     * @return 수정 성공 여부 반환
     */
    @AuthCheckAop("UPDATE_CALENDAR")
    @PostMapping("admin/calendar/update")
    @ResponseBody
    public ResponseEntity<String> updateCalendar(@RequestBody CalendarVo vo, HttpSession session) {        // JSON 데이터를 받기 위해 @RequestBody
        AdminVo loginAdminVo = (AdminVo) session.getAttribute("loginAdminVo");
        String no = loginAdminVo.getNo();
        vo.setAdminNo(no);
        int result = service.updateCalendar(vo);
        return result == 1 ? ResponseEntity.ok("스케줄 수정 성공!") : ResponseEntity.internalServerError().body("스케줄 수정 실패");
    }

    /**
     * 캘린더 일정 삭제 요청
     *
     * @param calendarNo 캘린더번호
     * @param session    작성자 번호 알기위함
     * @return 일정 삭제 성공 여부 반환
     */
    @AuthCheckAop("DELETE_CALENDAR")
    @PostMapping("admin/calendar/delete")
    @ResponseBody
    public ResponseEntity<String> deleteCalendar(@RequestParam("calendarNo") String calendarNo, HttpSession session) {
        AdminVo loginAdminVo = (AdminVo) session.getAttribute("loginAdminVo");
        String adminNo = loginAdminVo.getNo();
        int result = service.deleteCalendar(adminNo, calendarNo);
        return result == 1 ? ResponseEntity.ok("스케줄 삭제 완료!") : ResponseEntity.internalServerError().body("스케줄 삭제 실패");
    }
}//class
