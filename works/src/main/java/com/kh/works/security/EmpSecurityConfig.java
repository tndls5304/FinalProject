package com.kh.works.security;

import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;


@Configuration
@Order(1)
public class EmpSecurityConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/css/**","/js/**","/img/**");
    }

    @Bean
    public SecurityFilterChain empSecurityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("test");
        http.securityMatcher("/**")
                .authorizeHttpRequests((requests) -> requests
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
//                        //서버에요청오는 url이고 ,permitAll() 이건 허용해준다라는 의미.
                        .requestMatchers("/**").permitAll()
                        //이것말고 어떤 다른건 인증이 필요하다라는 뜻.
                        .anyRequest().authenticated()

                )
                .formLogin((form) -> form
                        .loginPage("/emp/login").permitAll()
                                             ///emp/login 페이지는  인증 안해도 허용하겠다라는뜻
                                            //로그인페이지의 submit누르면 이 url로 호출하겠다
                                .loginProcessingUrl("/userAuth")
                        .defaultSuccessUrl("/").permitAll()
                                            //loginProcessingUrl() 여기서 성공하면 어느페이지에 redirect할지 설정
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

}
