package org.xujin.springboot.config;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.xujin.springboot.ScoreRanking;


@Aspect
@Order(-2147483646)
@Configuration
public class DataSourceAutoAspect {

    @AfterReturning(value = "execution(* org.xujin.springboot.service..*(..)) && (@annotation(scoreRanking) || @target(scoreRanking))",returning = "keys")
    public void doAfterReturningAdvice1(JoinPoint joinPoint, Object keys, ScoreRanking scoreRanking){
        System.out.println(keys);
        System.out.println("kind:"+joinPoint.getKind());
        System.out.println("target:"+joinPoint.getTarget());
        System.out.println("this:"+joinPoint.getThis());
        System.out.println("toShortString:"+joinPoint.toShortString());
        System.out.println("toLongString:"+joinPoint.toLongString());
        System.out.println("getSignature:getDeclaringType:"+joinPoint.getSignature().getDeclaringType());
        System.out.println("getSignature:getModifiers"+joinPoint.getSignature().getModifiers());
        System.out.println("getSignature:getName"+joinPoint.getSignature().getName());
        System.out.println("getSignature:getSignature:toLongString"+joinPoint.getSignature().toLongString());
        System.out.println("getSignature:getSignature:toShortString"+joinPoint.getSignature().toShortString());
        Object[] args = joinPoint.getArgs();
        System.out.println(args);
    }
}
