package com.example.blog.board;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor // DB에서 조회해서 가져온 ResultSet(RS)를 디폴트 생성자를 호출해서 인스턴스를 생성하고 값을 채워줌
@Getter
@Table(name = "board_tb") // DB table 이름 설정
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment 주입
    private Integer id;
    private String title;
    private String content;
    private Timestamp createdAt;
}