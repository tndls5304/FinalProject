//-------------------------------수인-----------------------------------
package com.kh.works.employee.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.works.admin.email.entity.EmailMessage;
import com.kh.works.admin.email.service.EmailService;
import com.kh.works.employee.service.EmpAccountService;
import com.kh.works.employee.vo.EmployeeVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class EmpAccountController {
    private final BCryptPasswordEncoder encoder;
    private final EmpAccountService service;
    private final EmailService emailService;
    private final AmazonS3 s3; //결합도를 낮추기위해 부모타입을 쓰겠다 A s3 client는 자식💗

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    //가입페이지 보여주기 (관리자가 가입암호키 발급해준거 파라미터로 받고 model에 넣어서 화면으로 전달하기)
    @GetMapping("emp/join")
    public String empJoin(@RequestParam(value = "key") String joinKey, Model model) {
        Boolean isValid = service.checkJoinExpiration(joinKey);
        if (isValid) {
            model.addAttribute("joinKey", joinKey);
        } else {
            model.addAttribute("errorMsg", "회원가입 웹페이지 기간이 만료되었습니다.");
        }
        return "account/emp/join";
    }

    //아이디중복검사하기
    @GetMapping("emp/join_duplicateTest")
    @ResponseBody
    public String empJoinDuplicateTest(@RequestParam(value = "id") String id, Model model) {
        int numOfduplicate = service.duplicateTest(id);
        return numOfduplicate == 1 ? "중복된 아이디입니다❌️" : "사용가능한 아이디입니다✔";
    }

    //회원가입하기
    @PostMapping("emp/join")
    public String join(EmployeeVo vo, Model model, RedirectAttributes redirectAttributes) {
        try {
            MultipartFile profileInfo = vo.getProfileInfo();

            if (profileInfo == null || profileInfo.isEmpty()) {
                throw new Exception("회원가입 실패! 사진 파일이 없습니다");
            }
            /*s3에 업로드하기
             * s3.putObject(버킷,파일이름,인풋스트림,파일상세정보); 이런식으로 쓰는데 인풋스트림을 넘긴다고?? 인풋스트림만 넘기면 아마존이 알아서 해준다.
             * 파일 상세정보는 객체를 만들어서 넣어줘야 해서 */
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(profileInfo.getContentType());
            metadata.setContentLength(profileInfo.getSize());
            s3.putObject(bucketName, profileInfo.getOriginalFilename(), profileInfo.getInputStream(), metadata); //인풋스트림을 넘긴다고?? 인풋스트림만 넘기면 아마존이 알아서 해준다.
            //내가 금방올린 파일 url 가져오기
            URL url = s3.getUrl(bucketName, profileInfo.getOriginalFilename());
            String urlText = url.toString();
            vo.setProfile(urlText);

            int result = service.join(vo);
            if (result == 1) {
                redirectAttributes.addFlashAttribute("joinSuccessMsg", "회원가입 성공! 로그인해주세요.");
                return "redirect:/emp/login";
            } else {
                throw new Exception("회원가입실패! 다시 시도해주세요[err01]");
            }
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "account/emp/join";
        }
    }

    // 로그인페이지
    @GetMapping("emp/login")
    public String emplogin() {
        return "account/emp/login";
    }

    //로그인하기
    @PostMapping("emp/login")
    public String empLoginIdMatching(EmployeeVo vo, HttpSession session, Model model) {

        //1.일단 아이디 일치 확인
        EmployeeVo loginEmpVo = service.empLoginIdMatching(vo);

        if (loginEmpVo == null) {
            model.addAttribute("errorMsg", "일치하는 아이디가 없습니다 아이디 확인 후 다시 시도 해주세요!");
            return "account/emp/login";
        }

        //2.로그인실패 횟수 3회 이상인지 확인
        int loginFailNum = Integer.parseInt(loginEmpVo.getLoginFailNum());
        String loginFailEmpNo = loginEmpVo.getNo();
        if (loginFailNum >= 3) {
            service.lockAccount(loginFailEmpNo);
            model.addAttribute("errorMsg", "3회실패 ❗ 계정잠금)관리자에게 문의하세요");
            return "account/emp/login";
        }
        //3.서비스단에서 사용자입력 평문비밀번호와 디비에 저장된 암호문을 비교하게함
        loginEmpVo = service.pwdMatching(vo, loginEmpVo);
        if (loginEmpVo == null) {
            model.addAttribute("errorMsg", "로그인실패!! 긴장하세요 3회 실패시에는 계정이 잠금됩니다");
            return "account/emp/login";
        }
        session.setAttribute("loginEmpVo", loginEmpVo);
        return "redirect:/home";
    }

    //자신의 아이디 찾기
    @PostMapping("emp/find-id")
    @ResponseBody
    public ResponseEntity<String> findId(EmployeeVo vo, Model model) {
        String id = service.findId(vo);
        // 아이디가 null이거나 비어 있는 경우
        if (!StringUtils.hasText(id)) {
            return ResponseEntity.internalServerError().body("일치하는 아이디가 없습니다 ");
        }
        StringBuilder sb = new StringBuilder(id);
        if (sb.length() > 3) {
            sb.replace(sb.length() - 3, sb.length(), "***");
        }
        return ResponseEntity.ok(sb.toString());
    }


    //자신의 비밀번호 찾기 -> 일치하는 이메일 찾아서 보여주기 ( 대신 앞 3글자 가려서)
    @PostMapping("emp/find-pwd")
    @ResponseBody
    public ResponseEntity<String> selectMailToFindPwd(EmployeeVo vo, Model model) throws JsonProcessingException {
        //입력받은 정보로 이메일과 사원번호 조회함
        EmployeeVo partVo = service.selectMailToFindPwd(vo);
        String email = partVo.getEmail();
        String empNo = partVo.getNo();

        if (!StringUtils.hasText(email)) {
            //클라이언트의 요청이 잘못된 것이므로 400 상태 코드를 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("일치하는 계정이 없습니다. 다시 입력해주세요!");
        }
        int atIndex = email.indexOf("@");
        // @가 없으면 atIndex -1 을 리턴
        if (atIndex == -1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 이메일 주소로 가입되어 있습니다 관리자에게 문의하세요.");
        }
        String emailId = email.substring(0, atIndex);
        String domain = email.substring(atIndex + 1);  // 도메인추출

        String star = emailId.length() <= 3 ? "***" : emailId.substring(0, emailId.length() - 3) + "***";

        String hintEmail = star + "@" + domain;

        Map<String, String> empPartData = new HashMap<>();
        empPartData.put("hintEmail", hintEmail);
        empPartData.put("empNo", empNo);

        String jsonStr = new ObjectMapper().writeValueAsString(empPartData);
        return ResponseEntity.ok(jsonStr);
        // 투스트링 결과물 ::: Map[title="zzz"] << JSON 아님 ...
        // {"title":"zzz"}
    }

    //이메일로 임시 비밀번호 전송하는 로직
    @PostMapping("emp/send/email/get/pwd")
    @ResponseBody
    public ResponseEntity<String> sendMailToFindPwd(@RequestParam("no") String no) {

        // 사원 정보를 조회하여 이메일 정보를 가져옴
        String email= service.getEmailByNo(no);
        if (!StringUtils.hasText(email)) {
            return ResponseEntity.badRequest().body("해당 사원의 이메일을 찾을 수 없거나 유효하지 않습니다.");
        }
        // 임시 비밀번호 생성
        String randomPwd = UUID.randomUUID().toString();

        // 비밀번호를 암호화하여 데이터베이스에 저장
        String encodedPwd = encoder.encode(randomPwd);

        // EmployeeVo 객체에 암호화된 비밀번호와 이메일 설정
        EmployeeVo vo =new EmployeeVo();
        vo.setNo(no);
        vo.setPwd(encodedPwd);

        // 비밀번호 업데이트
        int result = service.updatePwd(vo);
        //사원을 저장하고 이메일도 들고옴
        if (result==1) {
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
            return ResponseEntity.ok("임시비밀번호가 발급되었습니다 메일로 확인해주세요");
        }else {
            return ResponseEntity.internalServerError().body("임시 비밀번호 발급 실패. 다시 시도해주세요!");
        }
    }

    //멤버 로그아웃하기
    @GetMapping("emp/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/emp/login";
    }

}
