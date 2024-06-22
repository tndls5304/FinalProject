package com.kh.works.admin.controller;

import com.kh.works.admin.servcie.AdminCommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminCommonController {
    private final AdminCommonService service;

    //관리자 페이지의 사이드바 메뉴 가져오기
    @GetMapping("admin/common/select_sidebar")
    public String selectSidePageComponent(){
        return service.selectSidePageComponent();
    }
}
