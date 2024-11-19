package com.example.blog.board;

import lombok.Data;

public class BoardRequest {

    @Data // getter, setter, toString 생성
    public static class SaveDTO{
       private String title;
       private String content;
    }

    @Data
    public static class UpdateDTO{
        private String title;
        private String content;
    }
}
