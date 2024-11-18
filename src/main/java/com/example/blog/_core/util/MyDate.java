package com.example.blog._core.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate {
    public static String formatDate(Timestamp createdAt) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String format = sdf.format(createdAt);
        return format;
    }
}
