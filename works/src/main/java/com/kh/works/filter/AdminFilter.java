package com.kh.works.filter;

import com.kh.works.admin.vo.AdminVo;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
/**
 * 관리자 필터
 * @author 이수인
 * @since 2024. 08. 07.
 */
@Slf4j                // Lombok 어노테이션으로 Logger 객체를 생성
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("AdminFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession(false);

        AdminVo loginAdminVo = (session != null) ? (AdminVo) session.getAttribute("loginAdminVo") : null;

        // "loginAdminVo"가 null인 경우 로그인 페이지로 리디렉션하고+ 필터 체인 처리 중단🔸
        if (loginAdminVo == null  &&!"/admin/login".equals(httpRequest.getRequestURI())) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/admin/login");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("AdminFilter destroy");
    }
}



