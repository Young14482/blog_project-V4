package com.example.blog.board;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardResponse {

    @Data
    public static class DetailDTO {
        private int id;
        private String title;
        private String content;
        private String createdAt;

        public DetailDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();

            // TODO: 2024.11.18.형태로 변경
            Date date = new Date();
            date.setTime(board.getCreatedAt().getTime());

            this.createdAt = new SimpleDateFormat("yyyy.MM.dd").format(date);
        }
    }

    @Data
    public static class DTO {
        private int id;
        private String title;

        public DTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
        }
    }
}
