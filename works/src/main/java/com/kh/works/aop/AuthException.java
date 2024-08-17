package com.kh.works.aop;

/**
 * RuntimeException을 상속받아 새로운 권한예외 정의
 *
 * @author 이수인
 * @since 2024. 08. 15
 */
public class AuthException extends RuntimeException {
    public AuthException(String msg) {
        super(msg);
    }
}
