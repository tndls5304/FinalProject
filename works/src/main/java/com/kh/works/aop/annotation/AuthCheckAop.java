package com.kh.works.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})                     //클래스와 메서드에 적용
@Retention(RetentionPolicy.RUNTIME)                                 //어노테이션이 런타임떄도 어노테이션 정보를 참조하게함
public @interface AuthCheckAop {
    String value() default "";                                       // 기본값은 빈 배열로 함
}
