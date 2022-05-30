package hello.aop.member.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 메서드 레벨에 붙는 애노태이션
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodAop {
    String value();
}