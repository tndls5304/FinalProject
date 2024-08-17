package com.kh.works.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 권한체크 어노테이션 생성( 해당 권한체크 어노테이션이 있으면 해당 지점에서 어드바이스가 적용되게끔 함)
 *
 * @author 이수인
 * @since 2024. 08. 15
 */
@Target({ElementType.TYPE, ElementType.METHOD})                     //클래스와 메서드에 적용
@Retention(RetentionPolicy.RUNTIME)                                 //어노테이션이 런타임떄도 어노테이션 정보를 참조하게함
public @interface AuthCheckAop {
    String value() default "";                                       // 기본값은 빈 배열로 함
}
