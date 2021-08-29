package com.example.springaop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimerAop {

    @Pointcut("@annotation(com.example.springaop.annotations.Timer)")
    public void enableTimer() { }

    @Around("enableTimer()")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object obj = proceedingJoinPoint.proceed();  //실제 비즈니스 로직 수행

        stopWatch.stop();
        System.out.println("total time : " + stopWatch.getTotalTimeSeconds());
    }

}
