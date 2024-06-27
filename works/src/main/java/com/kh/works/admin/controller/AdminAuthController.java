package com.kh.works.admin.controller;

import com.kh.works.admin.servcie.AdminAuthService;
import com.kh.works.admin.vo.SubAdminMenuVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminAuthController {

    private final AdminAuthService  service;

    //페이지만 보여주기
    @GetMapping("admin/auth_manage")
    public String showAuthPage(){
        return "admin/auth_manage";
    }

    //서브관리자의 메뉴 보기
    @GetMapping("admin/get_sub_admin_menu")
    @ResponseBody
    public List<SubAdminMenuVo> getMenuVoList(){

        return service.getMenuVoList();
    }

    //서브관리자 권한 update
    @PostMapping("admin/update_auth")
    @ResponseBody
    public int updateAuth(SubAdminMenuVo vo){
        System.out.println("SubAdminMenuVo vo 잘받았다 "+vo);
        return 1;
    }
}
