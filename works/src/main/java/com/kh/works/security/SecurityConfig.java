package com.kh.works.security;
//------------------------------------------------------------수인
//시큐리티는 디스패처 서블릿 만나기전에 필터에서 작동함.
// 시큐리티 필터 ( 시큐리티가 필터를 상속받아서 구현이 되어 있다.)
// 컨피그에서는 시큐리티도 만들고 시큐리티 환경 설정도 함.


import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//Security 활성화 코드
@EnableWebSecurity          //시큐리티를 활성화해라
@Configuration
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {    //비밀번호 암호화 얘를 통해 비밀번호 암호화
        return new BCryptPasswordEncoder();
    }

    // 시큐리티 설정을 위한 빈 : 인증,인가 필요없이 다받아줌
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/css/**","/js/**","/img/**");
    }

    @Bean       //실제 경로별로 들어왔을때 어떤건 들여보내고 막는 역할
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
                        .defaultSuccessUrl("/admin/home").permitAll()
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
//
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
