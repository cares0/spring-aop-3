package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

@Slf4j
public class ProxyCastingTest {

    @Test
    void jdkProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false); //JDK 동적 프록시 생성
        // (생략도 가능, 프록시 팩토리는 인터페이스 기반일 경우는 JDK로 생성해버림,
        // 스프링이 자동으로 생성하는 경우만 모두 CGLIB)

        // 프록시를 인터페이스로 캐스팅할 시에는 성공함 -> 인터페이스 기반이기 때문
        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

        // 오류 발생, 캐스팅 불가임 ClassCastException 발생
        // MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;
        Assertions.assertThrows(ClassCastException.class, () ->
            {MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;}
        );
    }

    @Test
    void cglibProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true); // CGLIB 프록시 생성

        // 프록시를 인터페이스로 캐스팅할 시에 성공함 -> 구현체가 인터페이스를 구현하고 있기 때문
        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

        // 물론 구현체로도 캐스팅이 가능
        MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;

    }
}
