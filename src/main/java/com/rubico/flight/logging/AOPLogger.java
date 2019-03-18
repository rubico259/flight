package com.rubico.flight.logging;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AOPLogger {

    @Before("execution(* com.rubico.flight..*.*(..))")
    public void logMethodAccessBefore(JoinPoint joinPoint) {
        log.info("***** Starting: " + joinPoint.getSignature().getName() + " *****");
    }

    @AfterReturning("execution(* com.rubico.flight..*.*(..))")
    public void logMethodAccessAfter(JoinPoint joinPoint) {
        log.info("***** Completed: " + joinPoint.getSignature().getName() + " *****");
    }
}

