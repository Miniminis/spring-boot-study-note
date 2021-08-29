package com.example.springaop.aop;

import com.example.springaop.domain.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Aspect
@Component
public class DecodeAop {


    @Pointcut("@annotation(com.example.springaop.annotations.Decoder)")
    public void enableDecoder() { }

    @Before("enableDecoder()")
    public void before(JoinPoint joinPoint) {
        Object[] objects = joinPoint.getArgs();
        for (Object obj : objects) {
            if (obj instanceof User) {
                User user = User.class.cast(obj);
                String base64Email = user.getEmail();
                String email = new String(Base64.getDecoder().decode(base64Email.getBytes(StandardCharsets.UTF_8)));
                user.setEmail(email);
            }
        }
    }

    @AfterReturning(value = "enableDecoder()", returning = "obj")
    public void afterReturn(JoinPoint joinPoint, Object obj) {
        if (obj instanceof User) {
            User user = User.class.cast(obj);
            String plainEmail = user.getEmail();
            String encodedEmail = new String(Base64.getEncoder().encode(plainEmail.getBytes(StandardCharsets.UTF_8)));
            user.setEmail(encodedEmail);
        }
    }


    /**
     * method : requestPut
     * User : User(id=1, name=테스형, email=tester1234@gmail.com)
     * returnObj : User(id=1, name=테스형, email=tester1234@gmail.com)
     * */

}
