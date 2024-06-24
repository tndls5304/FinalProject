package com.kh.works.security;

import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//Security 활성화 코드 (수인:지우지마세용!)
@EnableWebSecurity
@Configuration
public class SecurityConfig {



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/css/**","/js/**","/img/**");
    }

    @Bean
    @Order(1)
    public SecurityFilterChain adminSecurityFilterChain(HttpSecurity http, AdminUserDetailsService service) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authenticationProvider(adminAuthenticationProvider(service))
                .securityMatcher("/admin/**")
                .authorizeHttpRequests((requests) -> requests
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/admin/login").permitAll()
//                        .usernameParameter("") 폼태그에 키이름 바꿀때 이거 쓰기
//                        .passwordParameter("")
                        .loginProcessingUrl("/admin/login_proc").permitAll()
                        //로그인 성공하면 이쪽으로 간다.
                        .defaultSuccessUrl("/admin/insert_emp").permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain empSecurityFilterChain(HttpSecurity http, EmpUserDetailsService service) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authenticationProvider(empAuthenticationProvider(service))
                .securityMatcher("/**")
                .authorizeHttpRequests((requests) -> requests
                                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                                .requestMatchers("/").permitAll()
                                .anyRequest().authenticated()

                )
                .formLogin(form -> form
                                .loginPage("/emp/login").permitAll()
                                .loginProcessingUrl("/emp/login_proc")
                                .defaultSuccessUrl("/home").permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider empAuthenticationProvider(EmpUserDetailsService service) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(service);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public DaoAuthenticationProvider adminAuthenticationProvider(AdminUserDetailsService service) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(service);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }


}
