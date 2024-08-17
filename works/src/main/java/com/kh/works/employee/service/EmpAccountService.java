package com.kh.works.employee.service;

import com.kh.works.admin.email.entity.EmailMessage;
import com.kh.works.admin.email.service.EmailService;
import com.kh.works.employee.dao.EmpAccountDao;
import com.kh.works.employee.vo.EmployeeVo;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.util.StringUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmpAccountService {
    private final EmailService emailService;
    private final EmpAccountDao dao;
    private final BCryptPasswordEncoder encoder;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //클라이언트 검증과 서버단검증 모두 하기
    public int join(EmployeeVo vo) throws Exception {
        if (vo.getId().length() > 30) {
            throw new Exception("아이디가 너무 깁니다 30글자이하로 적어주세요");
        }
        if (vo.getId().length() < 4) {
            throw new Exception("아이디가 너무 짧습니다 4글자 이상 적어주세요");
        }
        if (vo.getPwd().length() > 20) {
            throw new Exception("비밀번호가 너무 깁니다");
        }
        if (vo.getPwd().length() < 4) {
            throw new Exception("비밀번호가 너무 짧습니다");
        }
        if (!vo.getPwd().equals(vo.getPwdCheck())) {
            throw new Exception("비밀번호란과 비밀번호 확인란이 다릅니다");
        }
        if (vo.getName().length() > 30) {
            throw new Exception("이름이 너무 깁니다 ");
        }
        if (vo.getPhone().length() != 11) {
            throw new Exception("휴대폰번호는 11글자로 적어주세요 ");
        }
        if (!StringUtils.hasLength(vo.getProfile())) {
            throw new Exception("프로필사진이 없습니다 등록해주세요");
        }
        //비번 암호화
        String encStr = encoder.encode(vo.getPwd());
        vo.setPwd(encStr);
        return dao.join(vo);
    }

    public EmployeeVo empLoginIdMatching(EmployeeVo vo) {
        return dao.empLoginIdMatching(vo);
    }

    public int duplicateTest(String id) {
        return dao.duplicateTest(id);
    }

    public EmployeeVo pwdMatching(EmployeeVo vo, EmployeeVo loginEmpVo) {
        boolean isMatch = encoder.matches(vo.getPwd(), loginEmpVo.getPwd());
        if (isMatch) {
            return loginEmpVo;
        }
        String loginFailEmpNo = loginEmpVo.getNo();
        dao.plusLoginFailNum(loginFailEmpNo);
        return null;
    }

    public int lockAccount(String loginFailEmpNo) {
        return dao.lockAccount(loginFailEmpNo);
    }

    public String findId(EmployeeVo vo) {
        return dao.findId(vo);
    }

    public EmployeeVo selectMailToFindPwd(EmployeeVo vo) {
        return dao.selectMailToFindPwd(vo);
    }

    @Transactional
    public int updatePwdAndSendEmail(String no, String email) {
        // 임시 비밀번호 생성
        String randomPwd = UUID.randomUUID().toString();

        // 비밀번호를 암호화
        String encodedPwd = encoder.encode(randomPwd);

        // EmployeeVo 객체에 암호화된 비밀번호와 이메일 설정
        EmployeeVo vo = new EmployeeVo();
        vo.setNo(no);
        vo.setPwd(encodedPwd);
        //🔸비밀번호부터 변경해야됨 이메일보낸다음에 비밀번호 변경이 실패할경우에 이메일 보내기를 취소 못하기 떄문
        int result = dao.updatePwd(vo);
        //사원을 저장하고 이메일도 들고옴
        if (result == 1) {
            //임시 비밀 번호 저장 성공
            EmailMessage emailMessage = new EmailMessage();
            emailMessage.setTo(vo.getEmail());
            emailMessage.setSubject("baby works 운영자입니다~ 임시비밀번호 발급되었습니다");
            String mailContent = """
                    <!DOCTYPE html>
                         <html lang="en">
                             <head>
                                  <meta charset="UTF-8">
                                  <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                  <title>Document</title>
                             </head>
                             <body>
                                 <h2> 안녕하세요!baby works 회원님!</h2>
                                 <h4> 임시비밀 번호는 randomPwd 입니다 </h4>
                             </body>
                        </html>
                    """;
            mailContent = mailContent.replace("randomPwd", randomPwd);
            emailMessage.setMessage(mailContent);
            emailService.sendMail(emailMessage);
        }
        return result;
    }

    //회원가입할때 관리자가 등록후 30분 이내에 회원가입 가능
    public Boolean checkJoinExpiration(String joinKey) {
        // 현재 시간
        LocalDateTime now = LocalDateTime.now();
        //가입 날짜 시간
        String hireDateStr = dao.checkJoinExpiration(joinKey);
        if (hireDateStr == null) {
            throw new RuntimeException("가입날짜 누락");
        }
        // 문자열을 LocalDateTime으로 변환하기 위한 포맷 설정
        LocalDateTime hireDate;
        try {
            // 문자열을 LocalDateTime으로 변환
            hireDate = LocalDateTime.parse(hireDateStr, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("가입날짜 포맷 오류", e);
        }
        // 두 시간의 차이 계산
        Duration duration = Duration.between(hireDate, now);
        // 만료 여부 확인
        return duration.toMinutes() <= 30;
    }

    public String getEmailByNo(String no) {
        return dao.getEmailByNo(no);
    }
}
