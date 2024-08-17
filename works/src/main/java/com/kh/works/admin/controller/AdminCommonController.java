package com.kh.works.admin.controller;

import com.kh.works.admin.servcie.AdminCommonService;
import com.kh.works.admin.vo.AdminPageMenuVo;
import com.kh.works.admin.vo.AdminVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;

/**
 * 공통 사이드바 메뉴
 *
 * @author 이수인
 * @since 2024. 06.23
 */
@Controller
@RequiredArgsConstructor
public class AdminCommonController {

    private final AdminCommonService service;

    /**
     * 관리자 권한에 따라 사이드바 메뉴 가져오기  메뉴 이름,url 등등
     *
     * @param session 권리자 권한 번호를 알기 위함
     * @return 메뉴 리스트 응답
     */
    @GetMapping("admin/common/sidebar")
    @ResponseBody
    public ResponseEntity<List<AdminPageMenuVo>> selectSidePageComponent(HttpSession session) {
        AdminVo loginAdminVo = (AdminVo) session.getAttribute("loginAdminVo");
        String adminAuthNo = loginAdminVo.getAdminAuthorityNo();
        List<AdminPageMenuVo> menuList = service.selectSidePageComponent(adminAuthNo);
        if (menuList == null || menuList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(menuList);
    }
}
