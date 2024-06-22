package com.kh.works.security;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@Order(1)
public class EmpSecurityConfig {

    @Bean
    public SecurityFilterChain empSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/**")
                .authorizeHttpRequests((requests) -> requests
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers("/","/css/**","/js/**","/img/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/emp/login").permitAll()
                        .loginProcessingUrl("/userAuth").permitAll()
                        .defaultSuccessUrl("/").permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }


}
