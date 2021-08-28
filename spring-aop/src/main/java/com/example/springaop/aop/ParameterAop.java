package com.example.springaop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class ParameterAop {

    @Pointcut("execution(* com.example.springaop.controllers..*.*(..))")
    private void cut() {}

    @Before("cut()")
    public void beforeCut(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println("method : " + method.getName());

        Object[] objects = joinPoint.getArgs();
        for (Object obj : objects) {
            System.out.println(obj.getClass().getSimpleName() + " : " + obj);
        }
    }

    @AfterReturning(value = "cut()", returning = "obj")
    public void afterReturn(JoinPoint joinPoint, Object obj) {
        System.out.println("returnObj : " + obj);

        /**
         method : requestGet
         Long : 1
         String : steve
         returnObj : 1 : steve

         method : post
         User : User(id=1, name=steeeeeeeeve, email=steve@gmail.com)
         returnObj : User(id=1, name=steeeeeeeeve, email=steve@gmail.com)
         * */
    }
}
