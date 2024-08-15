package com.kh.works;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 *비밀번호 암호화  BCrypt는 빈등록
 * @author 이수인
 * @since 2024. 07. 18.
 */
@Configuration
public class PwdConfing {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
