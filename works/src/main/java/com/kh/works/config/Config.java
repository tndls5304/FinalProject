package com.kh.works.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer {

    //도메인 여러 개로도 가능하게 하고 싶어서 만들었는데,
    //일단 보류할게요.


    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080", "http://127.0.0.1:8080") //허용할 도메인
                .allowedMethods("GET", "POST", "PUT", "DELETE") //허용할 HTTP 메서드
                .allowedHeaders("*") //모든 헤더 허용
                .allowCredentials(true); //자격 증명 허용
    }
}
