package com.kh.works.admin.servcie;

import com.kh.works.admin.dao.AdminDao;
import com.kh.works.admin.email.entity.EmailMessage;
import com.kh.works.admin.email.service.EmailService;
import com.kh.works.admin.vo.DeptVo;
import com.kh.works.admin.vo.PositionVo;
import com.kh.works.employee.vo.EmployeeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//-----------------------------------------------수인
@Service
@RequiredArgsConstructor
public class AdminEmpService {

    private final EmailService emailService;
    private final AdminDao dao;
    private final BCryptPasswordEncoder encoder;

    //신규사원 등록하는 페이지: 옵션에 고를 수 있게 부서 조회해오기
    public List<DeptVo> selectDeptList() {
        return dao.selectDeptList();
    }

    @Transactional
    public int insertEmpAndSendEmail(EmployeeVo employeeVo) {
        //사원 등록하기전에 등록해야할 사원 no 가져오기
        String empNo = dao.getEmpSeqNo();
        //사원 no로 암호키 만듬
        String joinKey = encoder.encode(empNo);
        //회원에게 회원가입 키 생성해서 내려주기
        employeeVo.setJoinKey(joinKey);
        employeeVo.setNo(empNo);
        //사원등록부터하기
        int result = dao.insertEmp(employeeVo);
        //이메일보내기
        if (result == 1) {
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
                                 <a href="http://localhost:8080/join?key=joinKey"> [가입 등록 링크] </a>
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
        }

        return result;
    }

    public List<PositionVo> selectPosition() {
        return dao.selectPosition();
    }

    public EmployeeVo getEmpByNo(String empNo) {
        return dao.getEmpByNo(empNo);
    }

    public int editEmp(EmployeeVo vo) {
        return dao.editEmp(vo);
    }

    public int resignEmp(String empNo) {
        return dao.resignEmp(empNo);
    }

    //전체 사원조회 + 조건부사원검색
    public List<EmployeeVo> selectEmpByCondition(EmployeeVo vo) {
        return dao.selectEmpByCondition(vo);
    }
}
