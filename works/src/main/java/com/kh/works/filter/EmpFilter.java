package com.kh.works.filter;

import com.kh.works.employee.vo.EmployeeVo;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
/**
 * 사원 필터
 * @author 이수인
 * @since 2024. 08. 07.
 */
public class EmpFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession(false);

        EmployeeVo loginEmpVo = (session != null) ? (EmployeeVo) session.getAttribute("loginEmpVo") : null;

        // 세션에 사원vo가 null인 경우와 emp로 시작하는 페이지들은 로그인 페이지로 리디렉션하고+ 필터 체인 처리 중단🔸
        if (loginEmpVo == null&& !"/emp/login".equals(httpRequest.getRequestURI())) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/emp/login");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse); //다음필터로 가라 다음필터가 없으면 디스패처서블릿으로 진입
    }


    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
