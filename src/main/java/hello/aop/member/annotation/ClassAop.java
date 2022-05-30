package hello.aop.member.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // 클래스타입에 붙는 애노테이션임을 명시
@Retention(RetentionPolicy.RUNTIME) // 실제 실행시까지 애노테이션이 살아있음
public @interface ClassAop {
}
