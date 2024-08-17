package com.kh.works.aop;

import com.kh.works.admin.vo.AdminVo;
import com.kh.works.aop.annotation.AuthCheckAop;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 권한체크 어노테이션이 붙은 메서드 호출전에 권한 검사
 *
 * @author 이수인
 * @since 2024. 08. 15
 */
@Aspect
@Component
@RequiredArgsConstructor
public class AuthCheckAopHandler {

    private final AuthCheckService service;

    /**
     *타켓 메서드 전에 실행될 권한 검사
     * @param authCheckAop  AOP에서 타겟 메서드에 붙어 있는 AuthCheckAop 어노테이션의 인스턴스
     * @throws Throwable 부관리자번호 ("2")를 확인한 후 권한 검사후 예외가 나오면 @ControllerAdvice로 예외 던짐
     */
    @Before("@annotation(authCheckAop)")
    public void checkAuth(AuthCheckAop authCheckAop) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        AdminVo loginAdminVo = (AdminVo) session.getAttribute("loginAdminVo");
        String authNo = loginAdminVo.getAdminAuthorityNo();

        if ("2".equals(authNo)) {
            service.checkPermission(authCheckAop.value());                                                                           //authCheckAop.value() :어노테이션 괄호에 적힌 값
        }
    }
}



/*
< AOP 개념 >
공부하면서 이해하고 더 보고싶어서 기록🙂-
<예시) 부관리자가 사원퇴사처리 버튼을 눌렀을때 흐름>
    1. 클라이언트 요청: 부관리자가 사원 퇴사 처리 버튼을 클릭한다. 이 요청은 AdminAuthController 클래스의 resignEmp() 메서드를 호출한다.
    2. 안내원 역할인 프록시 객체 생성: Spring AOP 프레임워크는 AdminAuthController를 감싸는 프록시 객체를 생성한다. 이 프록시 객체는 AdminAuthController의 실제 메서드 호출을 제어한다.
    3. 어드바이스 적용: 프록시 객체는 @AuthCheckAop("RESIGN_EMP") 어노테이션을 확인하고, 사원 퇴사처리 요청을 가로챈다 그러곤 사원퇴사처리 메서드를 수행하기 전에 권한 검사를 먼저 수행한다.!!!!
                    AuthCheckAopHandler 클래스의 checkAuth() 메서드를 호출한다.
    4.어드바이스의 권한 검증:  AuthCheckAopHandler의 checkAuth() 메서드는 @Before 어드바이스를 사용하여 부관리자의 사원 퇴사 처리 권한이 있는지 검사
    5.검사 완료:권한 검증이 완료되면, 프록시 객체는 원래의 AdminAuthController 클래스의 인스턴스(즉, Target Object)로 돌아간다.
    6.Target Object로 돌아가서 원래 사원퇴사 메서드를 호출한다 : AdminAuthController의 resignEmp() 메서드가 실제로 호출되고, 사원 퇴사 처리 로직이 실행

<개념>
    **프록시객체는 안내원처럼 동작하여 요청을 가로채고, 검사를 수행한다.
    AdminAuthController를 감싸는 객체 메서드 호출 전후에 추가적인 작업(어드바이스)을 수행한다
    **joinPoint: 어드바이스로 오기전에 @AuthCheckAop 가 달린 실제 호출될 메서드의 정보
    **Target Object: 실제 비즈니스 로직을 구현하는 클래스의 인스턴스다.
    AdminAuthController의 인스턴스가 Target Object다. 이 객체는 실제 사원 퇴사 처리 로직을 수행한다.
    **어드바이스: 프록시 객체가 Target Object의 메서드 호출 전후에 수행하는 추가적인 작업이다. 권한 검증, 로깅, 트랜잭션 관리 등이 포함될 수 있다.
                포인트컷에 해당하는 메서드 호출 전에 실행. 특정 지점(포인트컷)에서 실행되는 코드 블록
    ** @Aspect: 주요메서드가 수행할때 권한검사나 트랜잭션 관리,로깅을 찍는 부가적인 작업을 할때 @Aspect를 쓰면됨.
                Aspect의 역할은 @Before,@After@Around같은 어드바이스로 그때 수행할 역할을 쓰면 되고
                 @Before("포인트컷 자리 ")  포인트컷은 어떤 조인트포인트에서  어드바이스를 적용할지 지점을 적는거

<AOP 순서 >
    1.Spring AOP는 @Aspect 어노테이션이 붙은 클래스를 기반으로 프록시 객체를 생성한다.
    2.프록시 객체는 @AuthCheckAop 달린 클래스(Target Object)를 감싼다.
    3.클라이언트가 @AuthCheckAop 달린 메서드 호출시에 프록시 객체가 호출을 가로챈다
    프록시 객체는 실제 Target Object의 메서드 호출 전에 설정된 어드바이스를 실행
    4.프록시 객체는 지금 여기 @Before 어드바이스를 실행
    5.프록시 객체가 여기 checkAuth() 메서드를 실행한다 (authCheckAop.value()를 통해 어노테이션의 값을 추출하고, 권한 검증 로직을 실행)
    6.권한 검증 로직이 완료되면,
    7.프록시 객체는 원래 자리였던 클라이언트가 호출한 메서드로 돌아가서  원래 해야했던 수정 ,삭제 뭐 이런 메서드를 호출을한다

   🔸🔸만약 권한검증할때 예외를 던진다면? 프록시 객체가 어드바이스를 적용하고, 예외가 발생하면 이를 클라이언트에게 직접 반환하지 않기 때문에 @ExceptionHandler을 만들어서 예외를 받아야 한다.

    checkAuth() 메서드에서 권한 검증을 수행하고, 권한이 없으면 예외를 던지는데
    checkPermission() 여기서 (authYn)이 N 이면  throw new AuthException(errorMsg); 예외를 던지게끔 했다. 이럴때는
    @ExceptionHandler(AuthException.class) 어노테이션이 붙은 메서드가 이 예외를 처리하게끔 했다.
    이 예외가 발생할떄는 예외가 발생하지 않았을때처럼 target 메서드를 다시 호출하진 않고
    ResponseEntity와 같은 HTTP 응답 객체를 생성하여 바로  클라이언트에게 반환!



        --------------------@AuthCheckAop가 달린 메서드에 대한 정보 추출하려면 ?-------------------------------------------
        Object target = joinPoint.getTarget(); // 타겟 객체
        String methodName = joinPoint.getSignature().getName(); // 프록시 객체가 checkAuth를 호출하기 전에 실제 호출될 메서드의 이름을 알 수 있는 것
        Object[] args = joinPoint.getArgs(); // 화면에서 전달한 인자값들을 배열로 가져옴
        // 어노테이션 값 추출
        String requiredPermission = authCheckAop.value();

        System.out.println("joinPoint???: " + joinPoint);
        System.out.println("Target object: " + target);
        System.out.println("Method name: " + methodName);
        System.out.println("Arguments: " + Arrays.toString(args));
        System.out.println("Required permission: " + requiredPermission);
        -----------------------------------------------------------------------------------------------------------------


        RequestContextHolder: 스프링 프레임워크에서 현재 스레드에 대한 요청 정보를 저장하고 관리하는 도구
                              요청과 관련된 정보를 스레드 로컬 저장소에 저장하기도 하고
                              스레드 로컬 저장소에서 현재 요청과 관련된 RequestAttributes 객체를 가져오거나 설정할 수 있는 도구
                              예를 들어, AOP에서 현재 요청의 세션 정보를 가져오거나, 트랜잭션 관리에서 요청 정보를 사용할 때 유용

        RequestAttributes : 클라이언트요청의 요청과 관련된 정보를 담고 있는 인터페이스.
                            이 객체는 현재 요청에 대한 다양한 속성(예: 세션 정보, 요청 파라미터 등)을 포함
                            RequestAttributes는  RequestContextHolder를 통해  스레드 로컬 저장소라는 장소에 저장된다.
                            각 스레드는 자신의 요청 정보를 담은 RequestAttributes 객체를 가지고 있다.
                            주로 HttpServletRequest나 HttpSession과 관련된 정보를 제공하는 역할을 한다.



        <스레드 로컬 저장소와 RequestContextHolder의 동작 정리 >

        1.요청 수신: 클라이언트의 요청이 들어오면, 서버는 해당 요청을 처리하기 위해 스레드를 사용

        2.스레드 로컬 저장소 설정: 각 스레드는 자신의 로컬 저장소를 가지며, 이 저장소는 ThreadLocal을 통해 구현된다.
        RequestContextHolder는 이 스레드 로컬 저장소에 요청과 관련된 정보를 저장한다

        3.RequestAttributes 저장:
        키와 값: ThreadLocal을 사용하여, 현재 스레드에 대한 RequestAttributes 객체를 저장한다.
        키: ThreadLocal 자체가 스레드에 따라 자동으로 구분되므로, 명시적인 키를 사용하지 않고 스레드 ID를 기반으로 자동으로 저장된다.
        값: 각 스레드는 자신과 관련된 RequestAttributes 객체를 가지고 있다.
        4.요청 처리 및 활용: 스레드가 요청을 처리하는 동안 RequestContextHolder를 통해 현재 스레드의 RequestAttributes 객체에 접근할 수 있다.
                             이를 통해 HTTP 요청 정보, 세션 데이터 등을 조회하고 사용할 수 있다.

       5. 요청 완료: 요청 처리가 완료되면, 스레드는 로컬 저장소에서 RequestAttributes 객체를 지우거나, 다음 요청을 처리할 때까지 계속 유지한다.
        */
