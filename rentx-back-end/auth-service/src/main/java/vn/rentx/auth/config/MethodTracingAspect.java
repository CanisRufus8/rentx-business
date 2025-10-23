package vn.rentx.auth.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodTracingAspect {

    @Before("execution(* vn.rentx.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println(">>> Called: "
                + joinPoint.getSignature().toShortString());
    }
}
