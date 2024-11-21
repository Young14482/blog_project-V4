package com.example.blog._core.error;

import com.example.blog._core.error.ex.Exception400;
import com.example.blog._core.error.ex.Exception404;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice // 이 어노테이션이 붙으면 "에러 처리" 라는 책임을 가지게 됨
public class MyControllerAdvice {

    @ResponseBody // return이 파일이름을 찾지 않고 내용을 던짐
    @ExceptionHandler(Exception400.class)
    public String err400(Exception400 e) {
        System.out.println("err400");
        // history.back(); >> 뒤로가기
        String body = """
                <script>
                    alert('${msg}');
                    history.back();
                </script>
                """.replace("${msg}", e.getMessage());

        return body;
    }

    @ResponseBody
    @ExceptionHandler(Exception404.class)
    public String err404(Exception404 e) {
        System.out.println("error404");
        String body = """
                <script>
                    alert('${msg}');
                    history.back();
                </script>
                """.replace("${msg}", e.getMessage());

        return body;
    }
}
