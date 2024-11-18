package com.example.blog.board._core.util;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class MyDataTest {
    @Test
    public void formatToStr_test() {
        // given
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());

        // when
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String format = sdf.format(createdAt);

        System.out.println(format);
    }

}
