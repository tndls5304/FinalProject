package com.kh.works.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
이 설정 파일 없이 EmpFilter 클래스만 생성하면 Spring Boot는 필터를 자동으로 서블릿 필터 체인에 등록하지 않는다.*/
/**
 * 사원 필터 설정파일
 * @author 이수인
 * @since 2024. 08. 07
 */
@Configuration
public class EmpFilterConfig {
    @Bean                           //
    public FilterRegistrationBean<EmpFilter> registerFilter() {
        EmpFilter filter = new EmpFilter();
        //FilterRegistrationBean은 Spring Boot에서 서블릿 필터를 등록하기 위한 클래스
        FilterRegistrationBean<EmpFilter> registrationBean = new FilterRegistrationBean<>(filter);
        registrationBean.addUrlPatterns("/emp/*");            // 필터가 모든 URL 패턴에 적용되도록 설정함
        return registrationBean;
    }
}

/*          <<동작원리: 라이프사이클 공부>>

1.애플리케이션 시작           : Spring 컨테이너 초기화
2.설정 클래스 스캔            : 스캔하면서 @Configuration 클래스 발견함
                            EmpFilterConfig 클래스를 스캔하여 이 클래스가 설정 클래스임을 인식
                            @Configuration 어노테이션이 붙어 있으므로, Bean 정의를 포함하고 있다고 판단
3.@Bean 메서드 호출
                            :Spring 컨테이너는 registerFilter() 메서드의 @Bean 어노테이션이 붙어 있기때문에 이 메서드를 호출한다
4.객체 생성 및 등록
                            :@Bean 어노테이션이 붙은 메서드는 Spring 컨테이너에 의해 호출되어 🔸반환된 객체를 빈으로 등록🔸한다.
                            :메서드 내부에서 EmpFilter 객체가 생성되고
                            EmpFilter 객체를 사용하여    FilterRegistrationBean<EmpFilter> 객체를 생성된다
                            이 FilterRegistrationBean 객체는 빈으로 등록이 되고 서블릿 필터 체인에 추가됨

FilterRegistrationBean 객체를 반환하고 이를 빈으로 등록함으로써, Spring Boot는 서블릿 필터 체인에 WorksFilter를 추가하고, 설정된 URL 패턴과 기타 설정을 적용할 수 있습니다.
* */


