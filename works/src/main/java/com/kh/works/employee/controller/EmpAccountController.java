//-------------------------------수인-----------------------------------

package com.kh.works.employee.controller;

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
import java.util.UUID;


@Controller
@RequiredArgsConstructor
public class EmpAccountController {

    private final EmpAccountService service;


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
    @PostMapping("emp/find_id")
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
}
