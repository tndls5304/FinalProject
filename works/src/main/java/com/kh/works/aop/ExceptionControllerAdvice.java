package com.kh.works.aop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    //AuthException이 RuntimeException을 상속받게 했기 때문에 먼저쓰기
    @ExceptionHandler(AuthException.class) //AuthException 타입의 예외가 던져지면 여기서 받아서 처리하겠다.
    public ResponseEntity<String> exception(AuthException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());            //클라이언트의 권한 에러 403 Forbidden
    }

    @ExceptionHandler(RuntimeException.class) //RuntimeException 타입 예외 발생하면 받아서 처리하겠다.
    public ResponseEntity<String> exception(RuntimeException RuntimeException) {
        return ResponseEntity.internalServerError().body(RuntimeException.getMessage());            //서버 문제 500 Internal Server Error
    }

}

