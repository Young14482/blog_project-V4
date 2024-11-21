package com.example.blog.board;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public class BoardRequest {

    @Data // getter, setter, toString 생성
    public static class SaveDTO{
        @NotBlank
        private String title;
        @NotBlank
        private String content;

        public Board toEntity(){
            Board board = new Board(null, title,content,null);
            return board;
        }
    }

    @Data
    public static class UpdateDTO{
        @NotBlank
        private String title;
        @NotBlank
        private String content;
    }
}
