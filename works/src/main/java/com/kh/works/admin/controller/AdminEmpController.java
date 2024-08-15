package com.kh.works.admin.controller;

import com.kh.works.admin.servcie.AdminEmpService;
import com.kh.works.admin.vo.AdminVo;
import com.kh.works.admin.vo.DeptVo;
import com.kh.works.admin.vo.PositionVo;
import com.kh.works.aop.annotation.AuthCheckAop;
import com.kh.works.employee.vo.EmployeeVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminEmpController {

    private final AdminEmpService adminEmpService;

    // 신규 사원 등록 페이지 로드
    @GetMapping("admin/insert_emp")
    public String insertEmp() {
        return "admin/insert_emp";
    }

    // 부서 조회해오기
    @GetMapping("admin/emp/dept")
    @ResponseBody
    public List<DeptVo> selectDeptList() {
        return adminEmpService.selectDeptList();
    }

    //직위명 조회해오기
    @GetMapping("admin/emp/position")
    @ResponseBody
    public List<PositionVo> selectPosition() {
        return adminEmpService.selectPosition();
    }

    //신규직원등록
    @AuthCheckAop("INSERT_EMP")
    @PostMapping("admin/insert_emp")
    public ResponseEntity<String> insertEmp(EmployeeVo employeeVo, HttpSession session) {
        AdminVo loginAdminVo = (AdminVo) session.getAttribute("loginAdminVo");
        int result = adminEmpService.insertEmpAndSendEmail(employeeVo);
        return result == 1 ? ResponseEntity.ok("회원등록완료하고 회원에게 가입초대 이메일 보내기 성공!")
                : ResponseEntity.internalServerError().body("회원등록과 이메일로 가입양식보내기 실패");
    }

    //사원관리 페이지 보내주기
    @GetMapping("admin/manage/employee")
    public String getEmpListPage() {
        return "admin/manage-employee";
    }

    //사원 상세보기
    @GetMapping("admin/employee/{empNo}")
    @ResponseBody
    public EmployeeVo getEmpByNo(@PathVariable("empNo") String empNo) {
        return adminEmpService.getEmpByNo(empNo);
    }

    //사원정보 수정하기
    @AuthCheckAop("UPDATE_EMP")
    @PostMapping("admin/employee")
    @ResponseBody
    public ResponseEntity<String> editEmp(@RequestBody EmployeeVo vo, HttpSession session) {
        int result = adminEmpService.editEmp(vo);
        return result == 1 ? ResponseEntity.ok("사원정보 수정하기 성공!")
                : ResponseEntity.internalServerError().body("회원정보 수정실패");
    }

    //사원퇴사 처리
    @AuthCheckAop("RESIGN_EMP")
    @PostMapping("admin/resign/employee/{empNo}")
    @ResponseBody
    public ResponseEntity<String> resignEmp(@PathVariable("empNo") String empNo, HttpSession session) {
        int result = adminEmpService.resignEmp(empNo);
        return result == 1 ? ResponseEntity.ok("퇴사처리 완료되었습니다")
                : ResponseEntity.internalServerError().body("퇴사처리 실패");
    }

    // 전체 사원 조회 그리고 조건부로 사원 검색
    @GetMapping("admin/select_emp_conditional")
    @ResponseBody
    public List<EmployeeVo> selectEmpByCondition(@ModelAttribute EmployeeVo vo) {
        return adminEmpService.selectEmpByCondition(vo);
    }
}
