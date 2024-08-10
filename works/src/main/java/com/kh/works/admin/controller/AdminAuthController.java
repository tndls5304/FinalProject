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

    private final AdminAuthService service;

    //페이지만 보여주기
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
    @PostMapping("admin/update/auth")
    @ResponseBody
    public String updateAuth(@RequestBody List<SubAdminMenuVo> list, HttpSession session) {
        //그 전에 서브어드민 이라면 해당 페이지의 권한이 있는지 체크하기
        AdminVo loginAdminVo = (AdminVo)session.getAttribute("loginAdminVo");
        String authNo = loginAdminVo.getAdminAuthorityNo();
        //2번 의미 권한이 2번째인 서브어드민권한이란뜻
        if ("2".equals(authNo)) {
            String authYn = service.checkAuthYn();
            if ("N".equals(authYn)) {
                return "수정 권한이 없습니다! super 관리자에게 권한 요청해주세요!";
            }
        }
        //권한수정하기
        int result = service.updateAuth(list);
        return result == 1 ? "권한 수정 성공⭐" : "권한 수정 실패";
    }
}

