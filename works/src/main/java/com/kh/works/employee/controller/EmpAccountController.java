package com.kh.works.employee.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 사원 계정 관리
 *
 * @author 이수인
 * @since 2024. 07. 18.
 */
@Controller
@RequiredArgsConstructor
public class EmpAccountController {
    private final BCryptPasswordEncoder encoder;
    private final EmpAccountService service;
    private final AmazonS3 s3;        // 결합도 낮추기 위해 AmazonS3Client s3Client=new AmazonS3Client();
    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    /**
     * 사원가입 양식 제공해주는 화면
     *
     * @param joinKey 관리자가 사원등록할때 사원번호를 암호화하고 파라미터에 넘겨줌
     * @param model
     * @return 유효기간 체크 후 가입화면 url로 이동
     */
    @GetMapping("join")
    public String empJoin(@RequestParam(value = "key") String joinKey, Model model) {
        Boolean isValid = service.checkJoinExpiration(joinKey);
        model.addAttribute(isValid ? "joinKey" : "errorMsg", isValid ? joinKey : "회원가입 웹페이지 기간이 만료되었습니다.");
        return "account/emp/join";
    }

    /**
     * 사원 회원가입시 아이디 중복 검사
     *
     * @param id
     * @param model
     * @return 클라이언트가 입력한 아이디 중복여부를 문자열로 응답
     */
    @GetMapping("emp/join_duplicateTest")
    @ResponseBody
    public String empJoinDuplicateTest(@RequestParam(value = "id") String id, Model model) {
        int numOfduplicate = service.duplicateTest(id);
        return numOfduplicate == 1 ? "중복된 아이디입니다❌️" : "사용가능한 아이디입니다✔";
    }

    /**
     * 사원 회원가입 요청
     *
     * @param vo                 클라이언트의 회원가입 정보
     * @param model
     * @param redirectAttributes
     * @return 회원가입 성공하면 로그인페이지로 리다이렉트, 실패하면 에러메세지 전달
     */
    @PostMapping("emp/join")
    public String join(EmployeeVo vo, Model model, RedirectAttributes redirectAttributes) {
        try {
            MultipartFile profileInfo = vo.getProfileInfo();

            if (profileInfo == null || profileInfo.isEmpty()) {
                throw new RuntimeException("회원가입 실패! 사진 파일이 없습니다");
            }
            /*s3에 업로드하기
             * s3.putObject(버킷,파일이름,인풋스트림,파일상세정보); 이런식으로 쓰는데 인풋스트림을 넘긴다고??인풋스트림만 넘기면 아마존이 알아서 해준다.
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
            if (result == 1) {      //클라한테 다른 URL로 새 요청을 하라고 해야니까 모델못씀 모델은 딱  뷰 렌더링 사이까지 데이터 전달
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

    /**
     * 사원 로그인 화면 제공
     *
     * @return 화면 url
     */
    // 로그인페이지
    @GetMapping("emp/login")
    public String emplogin() {
        return "account/emp/login";
    }

    /**
     * 사원 로그인 요청
     *
     * @param vo      클라이언트의 로그인 정보
     * @param session
     * @param model
     * @return 사원 정보 일치시 홈으로 리다이렉트, 불일치면 모델에 문자열 담아서 현재 url로 보냄
     */
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

    /**
     * 아이디 찾기 :사원의 일부정보로 자신의 아이디 조회 요청
     *
     * @param vo    사원의 일부정보(이름,폰번호)
     * @param model
     * @return 일치하는게 없으면 클라이언트측 요청오류로 400 Bad Request 에러메세지를 응답, 일치하면 상태코드와 아이디 일부 마스킹해서 응답
     */
    @PostMapping("emp/find-id")
    @ResponseBody
    public ResponseEntity<String> findId(EmployeeVo vo, Model model) {
        String id = service.findId(vo);
        if (!StringUtils.hasText(id)) {                                     // 아이디가 null이거나 비어 있는 경우
            return ResponseEntity.badRequest().body("일치하는 아이디가 없습니다.");
        }
        StringBuilder sb = new StringBuilder(id);
        if (sb.length() > 3) {
            sb.replace(sb.length() - 3, sb.length(), "***");
        } else {
            sb.replace(sb.length() - 1, sb.length(), "*");
        }
        return ResponseEntity.ok(sb.toString());
    }

    /**
     * 비밀번호찾기: 비밀번호 조회 요청하면 일치하는 이메일 응답하기
     *
     * @param vo    사원일부 정보(이름,아이디)
     * @param model
     * @return 일치하는 이메일 조회시 없으면 404NOT_FOUND,일치하는 이메일이 있으면 사번과 이메일 일부 마스킹해서 응답
     * @throws JsonProcessingException
     */
    @PostMapping("emp/find-pwd")
    @ResponseBody
    public ResponseEntity<String> selectMailToFindPwd(EmployeeVo vo, Model model) throws JsonProcessingException {
        //이메일과 사원번호 조회함
        EmployeeVo partVo = service.selectMailToFindPwd(vo);
        String email = partVo.getEmail();
        String empNo = partVo.getNo();

        if (!StringUtils.hasText(email)) {          //사원이 입력한 정보로는 찾을 수 없기에 404번 에러
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("일치하는 계정이 없습니다. 다시 입력해주세요!");
        }
        int atIndex = email.indexOf("@");
        if (atIndex == -1) {                        // @가 없으면 사원이 이메일을 잘못 저장해둔거라 400번에러 atIndex -1 을 리턴함
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 이메일 주소로 가입되어 있습니다 관리자에게 문의하세요.");
        }
        //조회해온 이메일을 가공(마스킹)하는 작업
        String emailId = email.substring(0, atIndex);
        String domain = email.substring(atIndex + 1);

        String maskingId = emailId.length() <= 3 ? "***" : emailId.substring(0, emailId.length() - 3) + "***";
        String hintEmail = maskingId + "@" + domain;

        Map<String, String> empPartData = new HashMap<>();
        empPartData.put("hintEmail", hintEmail);
        empPartData.put("empNo", empNo);
        //(@RestController는 스프링이 알아서 제이슨으로 바꿔서 반환함)
        String jsonStr = new ObjectMapper().writeValueAsString(empPartData);
        return ResponseEntity.ok(jsonStr);
        // 투스트링 결과물 ::: Map[title="zzz"] << JSON 아님 .. 제이슨으로 변환{"title":"zzz"}
    }

    /**
     * 마스킹해서 조회한 이메일에 임의의 랜덤 비밀 번호를 생성한 후 전송 요청
     *
     * @param no 사원번호
     * @return 이메일 전송 성공 여부에 따라 상태 메세지 전송
     */
    //해당 이메일로 임시 비밀번호 전송
    @PostMapping("emp/send/email/get/pwd")
    @ResponseBody
    public ResponseEntity<String> sendMailToFindPwd(@RequestParam("no") String no) {
        // 사원 정보를 조회하여 이메일 정보를 가져옴
        String email = service.getEmailByNo(no);
        if (!StringUtils.hasText(email)) {
            return ResponseEntity.badRequest().body("해당 사원의 이메일을 찾을 수 없거나 유효하지 않습니다.");
        }
        // 임의의 랜덤 비밀번호 생성 후 업데이트
        int result = service.updatePwdAndSendEmail(no, email);
        return result == 1 ? ResponseEntity.ok("임시비밀번호가 발급되었습니다 메일로 확인해주세요") :
                ResponseEntity.internalServerError().body("임시 비밀번호 발급 실패. 다시 시도해주세요!");
    }

    /**
     * 사원 로그아웃 요청
     *
     * @param session
     * @return 로그아웃하고 로그인화면으로 리다이렉트
     */
    //멤버 로그아웃하기
    @GetMapping("emp/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/emp/login";
    }

}
