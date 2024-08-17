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
 *
 * @author 이수인
 * @since 2024. 07. 13
 */
@Controller
@RequiredArgsConstructor
public class AdminAccountController {

    private final AdminAccountService service;

    /**
     * 관리자 로그인 화면 제공
     *
     * @return 이동할 url
     */
    @GetMapping("admin/login")
    public String login() {
        return "admin/login";
    }

    /**
     * 관리자로그인하기
     *
     * @param vo
     * @param session
     * @param model
     * @return 성공이면 로그인화면 실패면 홈화면으로 리다이렉트
     */
    @PostMapping("admin/login")
    public String adminLoginMatching(AdminVo vo, HttpSession session, Model model) {
        AdminVo loginAdminVo = service.login(vo);
        if (loginAdminVo == null) {
            model.addAttribute("errorMsg", "아이디 비밀번호 확인후 다시 로그인 해주세요!");
            return "admin/login";
        } else {
            session.setAttribute("loginAdminVo", loginAdminVo);
            return "redirect:/admin/home";
        }
    }

    /**
     * 관리자 로그아웃요청
     *
     * @param session
     * @return 관리자 로그인 화면으로 리다이렉트
     */
    @GetMapping("admin/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }
}
