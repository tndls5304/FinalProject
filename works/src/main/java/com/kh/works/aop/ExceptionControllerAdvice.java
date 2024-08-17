package com.kh.works.aop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 특정 예외 처리
 *
 * @author 이수인
 * @since 2024. 08. 15
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * AuthException 타입의 예외가 던져지면 여기서 받아서 처리(AuthException이 RuntimeException을 상속받게 했기 때문에 먼저씀)
     *
     * @param exception 권한예외
     * @return 요청한 작업에 대한 권한이 없음을 의미하는 HTTP 403 Forbidden 응답코드와, 예외 메세지를 클라이언트에게 전달
     */
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<String> exception(AuthException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());                         //클라이언트의 권한 에러 403 Forbidden
    }

    /**
     * RuntimeException 타입 예외 발생하면 받아서 처리
     *
     * @param RuntimeException 런타임예외
     * @return 서버에 예기치못한 에러가 발생했음을 HTTP 500 Internal Server Error 상태 코드와 예외 메시지를 포함한 응답을 클라이언트에게 반환
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> exception(RuntimeException RuntimeException) {
        return ResponseEntity.internalServerError().body(RuntimeException.getMessage());                                  //서버 문제 500 Internal Server Error
    }
}

