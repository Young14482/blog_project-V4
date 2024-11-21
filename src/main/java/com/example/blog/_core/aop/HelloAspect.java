package com.example.blog._core.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class HelloAspect {

    @Before("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void helloValidation(JoinPoint jp) {
        Object[] args = jp.getArgs();
        for (Object arg : args) {
            if (arg instanceof Integer) {
                System.out.println((Integer) arg + "번!!! 반갑다!!!!");
            }
        }
    }
}
