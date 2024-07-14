
//--------------------------수인------------------------------------
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AdminEmpController {

    private static final Logger log = LoggerFactory.getLogger(AdminEmpController.class);
    @Autowired
   private final EmailService emailService;
private final AdminEmpService adminEmpService;


    // 신규 사원 등록 페이지 로드
    @GetMapping("admin/insert_emp")
    public String insertEmp(){
        return "admin/insert_emp";
    }

    // 부서 조회해오기
    @GetMapping("admin/select_dept")
    @ResponseBody
    public List<DeptVo> selectDeptList(){
        List<DeptVo>voList =adminEmpService.selectDeptList();
        return  voList;
    }

    //직위명 조회해오기
    @GetMapping("admin/select_position")
    @ResponseBody
    public List<PositionVo> selectPosition(){
        List<PositionVo> voList=adminEmpService.selectPosition();
        return voList;
        // return adminEmpService.selectPosition(); 로 변경 필요 의미없는 지역변수 사용 하지 말것
    }


    //신규직원등록페이지에서  신규 직원 등록 버튼누르면 동작! RequestBody:무조건 json
    @PostMapping("admin/insert_emp")
    public ResponseEntity<String> insertEmp(EmployeeVo employeeVo, HttpSession session){

        //TODO 서브관리자권한 2(완료)
        AdminVo loginAdminVo = (AdminVo) session.getAttribute("loginAdminVo");
        String authNo=loginAdminVo.getAdminAuthorityNo();

        //서브어드민이라면 권한체크하기(권한번호 2번: 서브어드민)
        if(authNo.equals("2")){                                         // "2".equals(authNo) 수정 필요 아래도 동일하게
            String authYn=adminEmpService.checkAuthYnForInsertEmp();
            if(authYn.equals("N")){ //포비든권한오류
                return  ResponseEntity.status(HttpStatus.FORBIDDEN).body("신규직원 등록 권한이 없습니다!");
            }
        }
        //사원 등록하고 동시에 회원번호 가져옴
        adminEmpService.insertEmp(employeeVo);
      //  이메일보내기:  @Options로 담아준 객체의 no를 가져와서 파라미터로
        EmailMessage emailMessage=new EmailMessage();

        emailMessage.setTo(employeeVo.getEmail());
        
        // String 문자를 합칠때는 Buffer 나 String.format 사용
        // 방법1 new StringBuffer().append(employeeVo.getName()).append("님 ! baby works 회원가입 양식입니다").toString();  
        // 방법2 String.format("%s 님 ! baby works 회원가입 양식입니다", employeeVo.getName()); // %s 에 변수가 들어감
        emailMessage.setSubject(employeeVo.getName()+"님 ! baby works 회원가입 양식입니다");

        String mailContent= """
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
                      발급받으신 [사원 번호]를 이용하여, 아래 링크에서 개인 정보를 등록해 주시기 바랍니다.
                      <a href="http://localhost:8080/emp/join?memberNo=empNo"> [가입 등록 링크] </a>
                      개인 정보 등록은 신속하게 처리해 주시면 감사하겠습니다. 이는 급여, 복지, 사내 시스템 접근 등 여러 중요한 절차와 관련이 있습니다.
                      등록 과정에서 궁금한 사항이 있거나 문제가 발생할 경우, 언제든지 인사팀으로 연락 주시기 바랍니다.
                      다시 한번, [baby works]의 일원이 되신 것을 환영하며, 앞으로의 활약을 기대하겠습니다.
                      감사합니다.
                      관리팀 드림
                      [02-237-7772]
                    </body>
               </html>
                """;
        String empNo=employeeVo.getNo();
        mailContent = mailContent.replace("empNo",empNo);

        emailMessage.setMessage(mailContent);

        emailService.sendMail(emailMessage);

        return ResponseEntity.ok("회원등록완료하고 회원에게 가입초대 이메일 보내기 성공!");
}


    //사원관리 페이지: 페이지 보여주기
    @GetMapping("admin/list_emp")
    public String getEmpListPage(){
        return "admin/emp_list";
    }


    //사원관리 페이지: 전체 사원 목록조회
   @GetMapping("admin/list_all_emp")
   @ResponseBody
    public List<EmployeeVo> getAllEmpList(){
        return adminEmpService.getAllEmpList();
   }

   //사원 상세보기
    @GetMapping("admin/emp_by_no")
    @ResponseBody
    public EmployeeVo getEmpByNo(@RequestParam ("no") String no){

        return adminEmpService.getEmpByNo(no);
    }

    //사원정보 수정하기
    @PostMapping("admin/edit_emp")
    @ResponseBody
    public ResponseEntity<String> editEmp(@RequestBody EmployeeVo vo,HttpSession session){

        //서브관리자 권한체크
        AdminVo loginAdminVo = (AdminVo) session.getAttribute("loginAdminVo");
        String authNo=loginAdminVo.getAdminAuthorityNo();

        //2번 의미 서브어드민!! 서브어드민이라면 권한체크하기
        if(authNo.equals("2")){
            String authYn=adminEmpService.checkAuthYnForUpdateEmpInfo();
            if(authYn.equals("N")){
               //⭐ 403오류 권한 없음  포비든
              return  ResponseEntity.status(HttpStatus.FORBIDDEN).body("❌접근 금지! 사원 정보 수정 권한이 없습니다❌ ");
            }
        }

        int result=adminEmpService.editEmp(vo);
        if(result==1){
            return ResponseEntity.ok("사원정보 수정하기 성공!");
        }else{
            return  ResponseEntity.internalServerError().body("회원정보 수정실패");
        }
        // 이런식으로 작성하면 가독성이 더좋음
        // result==1 ? ResponseEntity.ok("사원정보 수정하기 성공!") 
        //           : ResponseEntity.internalServerError().body("회원정보 수정실패");
    }


    //사원퇴사 처리
    @PostMapping("admin/resign_emp")
    @ResponseBody
    public ResponseEntity<String> resignEmp(@RequestParam("no")String no,HttpSession session){

        //서브관리자 권한체크
        AdminVo loginAdminVo = (AdminVo) session.getAttribute("loginAdminVo");
        String authNo=loginAdminVo.getAdminAuthorityNo();

        //2번 의미 서브어드민!! 서브어드민이라면 권한체크하기
        if(authNo.equals("2")){
            String authYn=adminEmpService.checkAuthYnForResignEmp();
            if(authYn.equals("N")){
                //⭐ 403오류 권한 없음  포비든
                return  ResponseEntity.status(HttpStatus.FORBIDDEN).body("❌접근 금지! 퇴사 처리 권한이 없습니다❌");
            }
        }

        int result=adminEmpService.resignEmp(no);
        if(result==1){
            return ResponseEntity.ok("퇴사처리 완료되었습니다");
        }else{
            return  ResponseEntity.internalServerError().body("퇴사처리 실패");
        }
    }

    //조건부 사원 조회
    @GetMapping("admin/select_emp_conditional")
    @ResponseBody
    public List<EmployeeVo> selectEmpByCondition(@ModelAttribute EmployeeVo vo){
        System.out.println("무ㅓ가왔을까???"+vo);
           return adminEmpService.selectEmpByCondition(vo);

    }
}
