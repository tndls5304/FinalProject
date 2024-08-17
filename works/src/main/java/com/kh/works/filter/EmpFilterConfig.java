package com.kh.works.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 사원 필터 설정파일
 * @author 이수인
 * @since 2024. 08. 07
 */
@Configuration
public class EmpFilterConfig {
    @Bean
    public FilterRegistrationBean<EmpFilter> registerFilter() {
        EmpFilter filter = new EmpFilter();
        FilterRegistrationBean<EmpFilter> registrationBean = new FilterRegistrationBean<>(filter);
        registrationBean.addUrlPatterns("/emp/*");
        return registrationBean;
    }
}



