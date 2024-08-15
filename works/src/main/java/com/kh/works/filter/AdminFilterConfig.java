package com.kh.works.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * 관리자 필터 설정파일
 * @author 이수인
 * @since 2024. 08. 07.
 */
@Configuration
public class AdminFilterConfig {
    @Bean                             //빈 객체를 생성하고 Spring 컨테이너에 등록
    public FilterRegistrationBean<AdminFilter> adminRegisterFilter() {
        AdminFilter adminFilter = new AdminFilter();
        FilterRegistrationBean<AdminFilter> registrationBean = new FilterRegistrationBean<>(adminFilter);
        registrationBean.addUrlPatterns("/admin/*");
        return registrationBean;
    }
}
