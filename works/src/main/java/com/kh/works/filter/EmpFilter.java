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




/*
서블릿 컨테이너에 도달하기 전에 필터가 요청을 처리한다.
HTTP 요청은 서블릿 컨테이너에 도달하기 전에 필터를 먼저 통과하기때문에
직접적으로 HttpServletRequest가 요청을 받는다. HTTP 요청에 대해 구체적인 정보( URI, 파라미터, 세션)에 대해 접근 할 수 있는건 HttpServletRequest다.
그런데 필터를 구현하려면 ServletRequest를 인자로 받아야 한다.
ServletRequest의 하위 인터페이스인 HttpServletRequest울 얻기 위해 형변환을 해서 httpRequest을 얻는다
httpRequest에서 세션을 빼서 확인!


request.getSession(true) :  세션이 있으면 기존 세션을 반환한다.
                            세션이 없으면 새로운 세션을 만들어서 반환한다.


request.getSession(false):   세션이 있으면 기존 세션을 반환한다.
                            세션이 없으면 새로운 세션을 생성하지 않고 null을 반환한다.
                            request.getSession()과 request.getSession(true)은 동일하다.


 */