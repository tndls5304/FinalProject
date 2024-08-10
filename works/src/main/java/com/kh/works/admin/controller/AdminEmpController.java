package com.kh.works.admin.controller;

import com.kh.works.admin.servcie.AdminEmpService;
import com.kh.works.admin.email.entity.EmailMessage;
import com.kh.works.admin.email.service.EmailService;
import com.kh.works.admin.vo.AdminVo;
import com.kh.works.admin.vo.DeptVo;
import com.kh.works.admin.vo.PositionVo;
import com.kh.works.employee.vo.EmployeeVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminEmpController {

    private final EmailService emailService;
    private final AdminEmpService adminEmpService;
    private final BCryptPasswordEncoder encoder;

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

    //신규직원등록페이지에서 신규 직원 등록 버튼누르면 동작!
    @PostMapping("admin/insert_emp")
    public ResponseEntity<String> insertEmp(EmployeeVo employeeVo, HttpSession session) {
        //우선 등록 권한체크하기
        AdminVo loginAdminVo = (AdminVo) session.getAttribute("loginAdminVo");
        String authNo = loginAdminVo.getAdminAuthorityNo();

        if ("2".equals(authNo)) {
            String authYn = adminEmpService.checkAuthYnForInsertEmp();
            if ("N".equals(authYn)) { //포비든권한오류
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("신규직원 등록 권한이 없습니다!");
            }
        }

        //사원 등록하기전에 등록해야할 사원 no 가져오기
        String empNo = adminEmpService.getEmpSeqNo();
        //사원 no로 암호키 만듬
        String joinKey = encoder.encode(empNo);
        //회원에게 회원가입 키 생성해서 내려주기
        employeeVo.setJoinKey(joinKey);
        employeeVo.setNo(empNo);
        //사원등록
        adminEmpService.insertEmp(employeeVo);
        //이메일보내기
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setTo(employeeVo.getEmail());
        emailMessage.setSubject(employeeVo.getName() + "님 ! baby works 회원가입 양식입니다");
        String mailContent = """
                <!DOCTYPE html>
                   <html lang="en">
                        <head>
                            <meta charset="UTF-8">
                            <meta name="viewport" content="width=device-width, initial-scale=1.0">
                            <title>Document</title>
                        </head>
                        <body>
                             안녕하세요!
                             [baby works] 의 가족이 되신 것을 진심으로 환영합니다!
                             신규 입사를 축하드리며, 원활한 업무 진행을 위해 다음과 같은 사항을 안내드리고자 합니다.
                             아래 링크를 눌러 개인 정보를 등록해 주시기 바랍니다.
                             <a href="http://localhost:8080/emp/join?key=joinKey"> [가입 등록 링크] </a>
                             개인 정보 등록은 신속하게 처리해 주시면 감사하겠습니다. 이는 급여, 복지, 사내 시스템 접근 등 여러 중요한 절차와 관련이 있습니다.
                             등록 과정에서 궁금한 사항이 있거나 문제가 발생할 경우, 언제든지 인사팀으로 연락 주시기 바랍니다.
                             다시 한번, [baby works]의 일원이 되신 것을 환영하며, 앞으로의 활약을 기대하겠습니다.
                             감사합니다.
                             관리팀 드림
                             [02-237-7772]
                        </body>
                   </html>
                """;

        mailContent = mailContent.replace("joinKey", joinKey);
        emailMessage.setMessage(mailContent);
        emailService.sendMail(emailMessage);
        return ResponseEntity.ok("회원등록완료하고 회원에게 가입초대 이메일 보내기 성공!");
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
    @PostMapping("admin/employee")
    @ResponseBody
    public ResponseEntity<String> editEmp(@RequestBody EmployeeVo vo, HttpSession session) {
        //서브관리자 권한체크
        AdminVo loginAdminVo = (AdminVo) session.getAttribute("loginAdminVo");
        String authNo = loginAdminVo.getAdminAuthorityNo();

        //2번 의미 서브어드민!! 서브어드민이라면 권한체크하기
        if ("2".equals(authNo)) {
            String authYn = adminEmpService.checkAuthYnForUpdateEmpInfo();
            if ("N".equals(authYn)) {
                //⭐ 403오류 권한 없음  포비든
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("❌접근 금지! 사원 정보 수정 권한이 없습니다❌ ");
            }
        }
        int result = adminEmpService.editEmp(vo);
        return result == 1 ? ResponseEntity.ok("사원정보 수정하기 성공!")
                : ResponseEntity.internalServerError().body("회원정보 수정실패");
    }

    //사원퇴사 처리
    @PostMapping("admin/resign/employee/{empNo}")
    @ResponseBody
    public ResponseEntity<String> resignEmp(@PathVariable("empNo") String empNo, HttpSession session) {

        //서브관리자 권한체크
        AdminVo loginAdminVo = (AdminVo) session.getAttribute("loginAdminVo");
        String authNo = loginAdminVo.getAdminAuthorityNo();

        //2번 의미 서브어드민!! 서브어드민이라면 권한체크하기
        if ("2".equals(authNo)) {
            String authYn = adminEmpService.checkAuthYnForResignEmp();
            if ("N".equals(authYn)) {
                //⭐ 403오류 권한 없음  포비든
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("❌접근 금지! 퇴사 처리 권한이 없습니다❌");
            }
        }
        int result = adminEmpService.resignEmp(empNo);
        return result == 1 ? ResponseEntity.ok("퇴사처리 완료되었습니다")
                : ResponseEntity.internalServerError().body("퇴사처리 실패");
    }

    //조건부+ 전체 사원 조회
    @GetMapping("admin/select_emp_conditional")
    @ResponseBody
    public List<EmployeeVo> selectEmpByCondition(@ModelAttribute EmployeeVo vo) {
        return adminEmpService.selectEmpByCondition(vo);
    }
}
