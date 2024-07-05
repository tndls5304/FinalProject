//-------------------------------수인-----------------------------------

package com.kh.works.employee.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.works.admin.email.entity.EmailMessage;
import com.kh.works.admin.email.service.EmailService;
import com.kh.works.employee.service.EmpAccountService;
import com.kh.works.employee.vo.EmployeeVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;


@Controller
@RequiredArgsConstructor
public class EmpAccountController {

    private final EmpAccountService service;
    private final EmailService emailService;


    //가입페이지 보여주기 (관리자가 사원no 발급해준거 파라미터로 받고 model에 넣어서 화면으로 전달하기)
    @GetMapping("emp/join")
    public String empJoin(@RequestParam(value = "memberNo") String memberNo, Model model) {

        model.addAttribute("memberNo", memberNo);
        return "join/emp_join";
    }


    //아이디중복검사하기
    @GetMapping("emp/join_duplicateTest")
    @ResponseBody
    public String empJoinDuplicateTest(@RequestParam(value = "id") String id, Model model) {

        int numOfduplicate = service.empJoinDuplicateTest(id);
        if (numOfduplicate == 1) {
            return "중복된 아이디입니다❌️";
        } else {
            return "사용가능한 아이디입니다✔";
        }
    }

    //회원가입하기
    @PostMapping("emp/join")
    public String join(EmployeeVo vo, Model model, RedirectAttributes redirectAttributes) {
        try {
            MultipartFile profileInfo = vo.getProfileInfo();

            if (!profileInfo.isEmpty()) {
                String originFileName = profileInfo.getOriginalFilename();

                String random = UUID.randomUUID().toString();
                String ext = originFileName.substring(originFileName.lastIndexOf("."));
                String changeName = System.currentTimeMillis() + "_" + random + ext;
                String filePath = "src/main/resources/static/img/profile/";

                InputStream is = profileInfo.getInputStream();
                FileOutputStream fos = new FileOutputStream(filePath + changeName);

                // 1024글자 담아줄 수있는 사이즈 버퍼바구니
                byte[] buf = new byte[1024];
                int size = 0;
                // 내가읽은데이터가 버퍼에 들어감
                // 바이트배열을 전달받으면 리턴값이 바이트 토탈 넘버(내가 몇글자읽었는지)를 준다
                while ((size = is.read(buf)) != -1) {
                    fos.write(buf, 0, size);
                }
                is.close();
                fos.close();

                vo.setProfile(changeName);
            }
            int result = service.join(vo);
            if (result == 1) {
                redirectAttributes.addFlashAttribute("joinSuccessMsg", "회원가입 성공! 로그인해주세요.");
                return "redirect:/emp/login";
            } else {
                throw new Exception("회원가입실패! 다시 시도해주세요[err01]");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMsg", e.getMessage());
            return "join/emp_join";
        }
    }


    // 로그인페이지
    @GetMapping("emp/login")
    public String emplogin() {
        return "login/emp_login";
    }



    //로그인하기
    @PostMapping("emp/login")
    public String empLoginIdMatching(EmployeeVo vo, HttpSession session, Model model) {
        EmployeeVo loginEmpVo = service.empLoginIdMatching(vo);

        if (loginEmpVo == null) {
            model.addAttribute("errorMsg", "일치하는 아이디가 없습니다 아이디 확인 후 다시 시도 해주세요!");
            return "login/emp_login";
        }

        //TODO 메소드 리턴값에 따라 처리해줄건 없는지 /계정 잠금 한 후에는 계정잠금 확인하는 곳이 없는데?
        int loginFailNum = Integer.parseInt(loginEmpVo.getLoginFailNum());
        String loginFailEmpNo = loginEmpVo.getNo();
        if (loginFailNum >= 3) {
            service.lockAccount(loginFailEmpNo);
            model.addAttribute("errorMsg", "3회실패 ❗ 계정잠금)관리자에게 문의하세요");
            return "login/emp_login";
        }

        if (!vo.getPwd().equals(loginEmpVo.getPwd())) {
            model.addAttribute("errorMsg", "로그인실패!! 긴장하세요 3회 실패시에는 계정이 잠금됩니다");
            service.plusLoginFailNum(loginFailEmpNo);

            return "login/emp_login";
        }

        session.setAttribute("loginEmpVo", loginEmpVo);
        return "redirect:/home";
    }


    //자신의 아이디 찾기
    @PostMapping("emp/find-id")
    @ResponseBody
    public ResponseEntity<String> findId(EmployeeVo vo, Model model){
        String id = service.findId(vo);

        if(id!=null){
            StringBuilder sb = new StringBuilder(id);
            sb.replace(sb.length() - 3, sb.length(), "***");
            return ResponseEntity.ok(sb.toString());
        }
        return ResponseEntity.internalServerError().body("일치하는 아이디가 없습니다 ");
    }


    //자신의 비밀번호 찾기 -> 일치하는 이메일 찾아서 보여주기 ( 대신 앞 3글자 가려서)
    @PostMapping("emp/find-pwd")
    @ResponseBody
    public ResponseEntity<String> selectMailToFindPwd(EmployeeVo vo, Model model) throws JsonProcessingException {
        EmployeeVo partVo  = service.selectMailToFindPwd(vo);
        String email=partVo.getEmail();
        String empNo=partVo.getNo();

        if(email !=null){
            int atIndex = email.indexOf('@');

            String emailId = email.substring(0, atIndex);
            String domain = email.substring(atIndex + 1);  // 도메인추출

            int emailIdLength = emailId.length();
            String star;
            if (emailIdLength <= 3) {
                star = "***";
            } else {
                star = emailId.substring(0, emailIdLength - 3) + "***"; // 부분적으로 *로 대체된 이메일아이디
            }

            String hintEmail;
            hintEmail=star + "@" + domain;

            Map<String, String> empPartData = new HashMap<>();
            empPartData.put("hintEmail", hintEmail);
            empPartData.put("empNo", empNo);

            String jsonStr = new ObjectMapper().writeValueAsString(empPartData);

            return ResponseEntity.ok(jsonStr);
            // 투스트링 결과물 ::: Map[title="zzz"] << JSON 아님 ...
            // {"title":"zzz"}
        }
        return ResponseEntity.internalServerError().body("일치하는 계정이 없습니다 다시 입력해주세요!");
    }


    @PostMapping("emp/send-email-to-find-pwd")
    @ResponseBody
    public ResponseEntity<String> sendMailToFindPwd(String no){

        //비밀번호찾을떄 자기 이메일로 임시 비밀번호 받기
        System.out.println("자신의 사원번호는??"+no);

        //임시비밀번호 만들기:UUID에서 하이픈제거해서 10글자 만들고 + 사원번호 붙여서 디비에 저장
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String randomPwd=uuid.substring(0, 10);
        randomPwd+=no;

        EmployeeVo vo=new EmployeeVo();
        vo.setPwd(randomPwd);
        vo.setNo(no);

        //저장하고 이메일주소도 가져옴
        int result= service.updatePwd(vo);

        if(result==1){
            //랜덤 비밀번호 디비에 저장 성공
            EmailMessage emailMessage=new EmailMessage();

            emailMessage.setTo(vo.getEmail());
            emailMessage.setSubject("baby works 운영자입니다~ 임시비밀번호 발급되었습니다");

            String mailContent= """
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
            String pwd=vo.getPwd();
            mailContent = mailContent.replace("randomPwd",pwd);

            emailMessage.setMessage(mailContent);

            emailService.sendMail(emailMessage);
            //---------------------------------------------
            //2.해당 회원번호에 이 임시비밀번호 디비에 저장하면서 이메일로 전송하고 알람띄어주기
            return  ResponseEntity.ok("임시비밀번호가 발급되었습니다 메일로 확인해주세요");

        }
        return ResponseEntity.internalServerError().body("임시비밀번호 발급실패 다시 시도해주세요!");

    }

}
