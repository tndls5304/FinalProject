package com.kh.works.admin.controller;

import com.kh.works.admin.servcie.AdminAuthService;
import com.kh.works.admin.vo.SubAdminMenuVo;
import com.kh.works.aop.annotation.AuthCheckAop;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/***
 *  상위 관리자가 부관리자에게 crud 권한을 부여
 *
 *  @author 이수인
 *  @since 2024. 07. 20
 */
@Controller
@RequiredArgsConstructor
public class AdminAuthController {

    private final AdminAuthService service;

    /**
     * 부관리자 권한관리하는 페이지 제공
     *
     * @return 이동할 url
     */
    @GetMapping("admin/authority")
    public String showAuthPage() {
        return "admin/authority";
    }

    /**
     * 부관리자가 가지고 있는 권한 조회 요청
     *
     * @return 각 페이지마다 권한여부 List 반환
     */
    @GetMapping("admin/menu/sub-admin")
    @ResponseBody
    public List<SubAdminMenuVo> getMenuVoList() {
        return service.getMenuVoList();
    }

    /**
     * 부관리자 권한 수정하기 요청
     *
     * @return 처리결과 메세지 응답
     */
    @AuthCheckAop("UPDATE_SUB_ADMIN_AUTH")
    @PostMapping("admin/update/auth")
    @ResponseBody
    public ResponseEntity<String> updateAuth(@RequestBody List<SubAdminMenuVo> list) {
        int result = service.updateAuth(list);
        return result == 1 ? ResponseEntity.ok("부관리자 권한 수정 성공") : ResponseEntity.internalServerError().body("권한수정 실패");
    }
}

