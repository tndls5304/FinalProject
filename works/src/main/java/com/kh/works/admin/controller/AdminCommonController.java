package com.kh.works.admin.controller;

import com.kh.works.admin.servcie.AdminCommonService;
import com.kh.works.admin.vo.AdminPageMenuVo;
import com.kh.works.security.AdminSessionVo;
import com.kh.works.security.EmpSessionVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminCommonController {

    private final AdminCommonService service;

      //관리자 번호에 따라 사이드바 메뉴 url 골라서  주기
    @GetMapping("admin/common/sidebar")
    @ResponseBody
    public List<AdminPageMenuVo> selectSidePageComponent(@AuthenticationPrincipal AdminSessionVo loginAdminVo){
        String adminAuthNo=loginAdminVo.getNo();

        return service.selectSidePageComponent(adminAuthNo);
    }
}
