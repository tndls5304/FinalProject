package com.kh.works.admin.controller;

import com.kh.works.admin.servcie.AdminAuthService;
import com.kh.works.admin.vo.SubAdminMenuVo;
import com.kh.works.aop.annotation.AuthCheckAop;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminAuthController {

    private final AdminAuthService service;

    @GetMapping("admin/authority")
    public String showAuthPage() {
        return "admin/authority";
    }

    //서브관리자가 볼 수 있는 메뉴목록과 각 권한을 조회해오기.
    @GetMapping("admin/menu/sub-admin")
    @ResponseBody
    public List<SubAdminMenuVo> getMenuVoList() {
        return service.getMenuVoList();
    }

    //서브관리자 권한 수정하기
    @AuthCheckAop("UPDATE_SUB_ADMIN_AUTH")
    @PostMapping("admin/update/auth")
    @ResponseBody
    public ResponseEntity<String> updateAuth(@RequestBody List<SubAdminMenuVo> list) {
        int result = service.updateAuth(list);
        return result == 1 ?  ResponseEntity.ok("부관리자 권한 수정 성공") : ResponseEntity.internalServerError().body("권한수정 실패");
    }
}

