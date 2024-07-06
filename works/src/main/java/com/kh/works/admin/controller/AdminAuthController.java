package com.kh.works.admin.controller;

import com.kh.works.admin.servcie.AdminAuthService;
import com.kh.works.admin.vo.AdminVo;
import com.kh.works.admin.vo.SubAdminMenuVo;
import jakarta.servlet.http.HttpSession;
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



    //서브관리자 권한 update처리하기
    @PostMapping("admin/update_auth")
    @ResponseBody
    public String updateAuth(@RequestBody List<SubAdminMenuVo> list, HttpSession session){

        //TODO 서브관리자권한1(완료)
        AdminVo loginAdminVo = (AdminVo) session.getAttribute("loginAdminVo");
        String authNo=loginAdminVo.getAdminAuthorityNo();
        //2번 의미 서브어드민!! 서브어드민이라면 권한체크하기
        if(authNo.equals("2")){
            String authYn=service.checkAuthYn();
            if(authYn.equals("N")){
                return "수정 권한이 없습니다! super 관리자에게 권한 요청해주세요!";
                }
        }

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
