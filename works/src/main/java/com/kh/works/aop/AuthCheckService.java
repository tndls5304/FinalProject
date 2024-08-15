package com.kh.works.aop;

import com.kh.works.admin.dao.AdminAuthDao;
import com.kh.works.admin.dao.AdminCalendarDao;
import com.kh.works.admin.dao.AdminDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthCheckService {

    private final AdminCalendarDao authAdminCalendarDao;
    private final AdminDao adminDao;
    private final AdminAuthDao adminAuthDao;

    // 각 권한 체크 메서드
    public void checkPermission(String requiredPermission) {
        String authYn = "N";
        String errorMsg = "";
        switch (requiredPermission) {
            case "UPDATE_SUB_ADMIN_AUTH":
                authYn = adminAuthDao.checkSubAdminAuthYn();
                errorMsg = "접근금지❌ 서브어드민의 권한을 수정하는 페이지입니다 super 관리자에게 권한 요청 해주세요";
                break;
            case "INSERT_CALENDAR":
                authYn = authAdminCalendarDao.checkAuthYnForInsertCalendar();
                errorMsg = "접근금지❌ 일정 등록 작성 권한이 없습니다";
                break;
            case "UPDATE_CALENDAR":
                authYn = authAdminCalendarDao.checkAuthYnForUpdateCalendar();
                errorMsg = "접근금지❌ 일정 수정 권한이 없습니다";
                break;
            case "DELETE_CALENDAR":
                authYn = authAdminCalendarDao.checkAuthYnForDeleteCalendar();
                errorMsg = "접근금지❌일정 삭제 권한이 없습니다";
                break;
            case "INSERT_EMP":
                authYn = adminDao.checkAuthYnForInsertEmp();
                errorMsg = "접근금지❌신규 직원 등록 권한이 없습니다";
                break;
            case "UPDATE_EMP":
                authYn = adminDao.checkAuthYnForUpdateEmpInfo();
                errorMsg = "접근금지❌사원 수정 권한이 없습니다";
                break;
            case "RESIGN_EMP":
                authYn = adminDao.checkAuthYnForResignEmp();
                errorMsg = "접근금지❌사원 퇴사 권한이 없습니다";
                break;
        }
        if ("N".equals(authYn)) {
            throw new AuthException(errorMsg);
        }
    }
}
