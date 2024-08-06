package com.kh.works.admin.controller;

import com.kh.works.admin.servcie.AdminAccountService;
import com.kh.works.admin.vo.AdminVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 관리자 계정 관리
 * @author lee suin
 * @since 2024. 07. 13.
 */
@Controller
@RequiredArgsConstructor
public class AdminAccountController {

    private final AdminAccountService service;

    //로그인페이지보여주기
    @GetMapping("admin/login")
    public String login() {
        return "admin/login";
    }

/**
* 관리자로그인하기
* @param vo
* @param session
* @param model
* @return
*/
@PostMapping("admin/login")
public String adminLoginMatching(AdminVo vo, HttpSession session, Model model){
    AdminVo loginAdminVo =service.login(vo);

    if(loginAdminVo==null){
        model.addAttribute("errorMsg","아이디 비밀번호 확인후 다시 로그인 해주세요!");
        return "admin/login";
    }else{
        session.setAttribute("loginAdminVo",loginAdminVo);
        return "redirect:/admin/home";
    }
}


    //로그아웃시키기
    @GetMapping("admin/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/admin/login";
    }
}
