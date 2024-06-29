package com.kh.works.admin.controller;

import com.kh.works.admin.servcie.AdminAuthService;
import com.kh.works.admin.vo.SubAdminMenuVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    //ajax로 보낸 데이터를 서브관리자 권한 update처리하기
    @PostMapping("admin/update_auth")
    @ResponseBody
    public String updateAuth(@RequestBody List<SubAdminMenuVo> list){
        System.out.println("SubAdminMenuVo vo 잘받았다 "+list);

        int  result= service.updateAuth(list);


        String str;
        if(result==1){
            str="권한 수정 성공⭐";
        }else{
            str="권한 수정 실패";
        }
        return str;
    }
}
