package vn.io.vutiendat3601.sho.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
  @Pointcut("execution(* vn.io.vutiendat3601.sho.service.*.*(..))")
  public void serviceMethods() {}

  @Before("serviceMethods()")
  public void logBefore(JoinPoint joinPoint) {
    System.out.println("Before: " + joinPoint.getSignature().getName());
  }

  @AfterReturning(pointcut = "serviceMethods()", returning = "result")
  public void logAfter(Object result) {
    System.out.println("After Returning: " + result);
  }
}
