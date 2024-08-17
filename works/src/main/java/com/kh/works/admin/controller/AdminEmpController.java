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

/**
 * 관리자의 사원관리
 *
 * @author 이수인
 * @since 2024. 06. 29
 */
@Controller
@RequiredArgsConstructor
public class AdminEmpController {

    private final AdminEmpService adminEmpService;

    /**
     * 사원등록 페이지 제공
     *
     * @return 이동할 url
     */
    @GetMapping("admin/insert_emp")
    public String insertEmp() {
        return "admin/insert_emp";
    }

    /**
     * 모든 부서 조회 요청
     *
     * @return 조회 결과 응답
     */
    @GetMapping("admin/emp/dept")
    @ResponseBody
    public ResponseEntity<List<DeptVo>> selectDeptList() {
        List<DeptVo> deptList = adminEmpService.selectDeptList();
        if (deptList == null || deptList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(deptList);
    }

    /**
     * 모든 직위명 조회 요청
     *
     * @return 조회 결과 응답
     */
    @GetMapping("admin/emp/position")
    @ResponseBody
    public ResponseEntity<List<PositionVo>> selectPosition() {
        List<PositionVo> positionList = adminEmpService.selectPosition();
        if (positionList == null || positionList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(positionList);
    }

    /**
     * 신규 직원 등록하고 동시에 입력받은 이메일로 가입 양식 전송
     *
     * @param employeeVo 이메일을 포함한 사원정보
     * @return 결과 응답
     */
    @AuthCheckAop("INSERT_EMP")
    @PostMapping("admin/insert_emp")
    public ResponseEntity<String> insertEmp(EmployeeVo employeeVo) {
        int result = adminEmpService.insertEmpAndSendEmail(employeeVo);
        return result == 1 ? ResponseEntity.ok("회원등록완료하고 회원에게 가입초대 이메일 보내기 성공!")
                : ResponseEntity.internalServerError().body("회원등록과 이메일로 가입양식보내기 실패");
    }

    /**
     * 사원관리 페이지 제공
     *
     * @return 이동할 페이지 url
     */
    @GetMapping("admin/manage/employee")
    public String getEmpListPage() {
        return "admin/manage-employee";
    }

    /**
     * 사원 상세 조회
     *
     * @param empNo 사원번호
     * @return 사원 정보 반환
     */
    @GetMapping("admin/employee/{empNo}")
    @ResponseBody
    public EmployeeVo getEmpByNo(@PathVariable("empNo") String empNo) {
        return adminEmpService.getEmpByNo(empNo);
    }

    /**
     * 사원 정보 수정
     *
     * @param vo 수정할 사원 정보
     * @return 수정 성공 여부에 따라 상태코드, 메세지 응답
     */
    @AuthCheckAop("UPDATE_EMP")
    @PostMapping("admin/employee")
    @ResponseBody
    public ResponseEntity<String> editEmp(@RequestBody EmployeeVo vo) {
        int result = adminEmpService.editEmp(vo);
        return result == 1 ? ResponseEntity.ok("사원정보 수정하기 성공!")
                : ResponseEntity.internalServerError().body("회원정보 수정실패");
    }

    /**
     * 사원 퇴사 처리
     *
     * @param empNo 사원번호
     * @return 처리 성공 여부에 따라 상태코드,메세지 응답
     */
    @AuthCheckAop("RESIGN_EMP")
    @PostMapping("admin/resign/employee/{empNo}")
    @ResponseBody
    public ResponseEntity<String> resignEmp(@PathVariable("empNo") String empNo) {
        int result = adminEmpService.resignEmp(empNo);
        return result == 1 ? ResponseEntity.ok("퇴사처리 완료되었습니다")
                : ResponseEntity.internalServerError().body("퇴사처리 실패");
    }

    /**
     * 화면 로드될때 전체 사원 조회 혹은 검색 기능( 특정 조건일때 사원 검색 요청)
     * @param vo 조회 조건 (이름이나 퇴사여부..)
     * @return 사원정보가 담긴 리스트로 응답
     */
    @GetMapping("admin/select_emp_conditional")
    @ResponseBody
    public List<EmployeeVo> selectEmpByCondition(@ModelAttribute EmployeeVo vo) {
        return adminEmpService.selectEmpByCondition(vo);
    }
}
