package com.example.blog._core.aop;

import com.example.blog._core.error.ex.Exception400;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
@Aspect
public class MyValidationAspect {

    // 포인트 컷

    // 행위
    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping)") // 포인트컷 자리 >> 어노테이션 임포트 문구
    // 모든 정보가 담기는 JoinPoint
    public void validationCheck(JoinPoint jp) {
        Object[] args = jp.getArgs(); // 메소드의 파라미터를 다 가져옴
        // 반복문을 통해 Errors가 있는지 확인
        for (Object arg : args) {
            if (arg instanceof Errors) {
                Errors errors = (Errors) arg; // 다운 캐스팅
                // errors 내부에 메시지가 존재하면 예외 생성
                if(errors.hasErrors()) {
                    String errMsg = errors.getFieldErrors().get(0).getField() +": " + errors.getFieldErrors().get(0).getDefaultMessage();
                    throw new Exception400(errMsg);
                }

            }
        }
    }

}