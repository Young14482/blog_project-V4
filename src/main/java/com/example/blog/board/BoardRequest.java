package com.example.blog.board;

import lombok.Data;

public class BoardRequest {

    @Data // getter, setter, toString 생성
    public static class SaveDTO extends Object{
       private String title;
       private String content;
    }

    @Data
    public static class UpdateDTO extends Object{
        private String title;
        private String content;
    }
}
