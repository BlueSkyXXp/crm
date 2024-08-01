package com.stone.common.utils;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    /**
     * 将毫秒时间戳转换为日期格式的字符串。
     *
     * @param timestamp 毫秒时间戳
     * @param pattern   日期时间格式模式，例如："yyyy-MM-dd HH:mm:ss"
     * @return 格式化的日期时间字符串
     */
    public static String timestampToDateString(long timestamp, String pattern) {
        // 将毫秒时间戳转换为Instant
        Instant instant = Instant.ofEpochMilli(timestamp);
        // 将Instant转换为LocalDateTime
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        // 定义日期时间格式化器
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        // 格式化日期时间
        return dateTime.format(formatter);
    }


    /**
     * 将毫秒时间戳转换为日期格式的字符串。
     *
     * @param timestamp 毫秒时间戳
     * @return 格式化的日期时间字符串
     */
    public static String timestampToDateString(long timestamp) {
        return timestampToDateString(timestamp, "yyyy-MM-dd HH:mm:ss");
    }

    public static void main(String[] args) {
        // 示例：当前时间的毫秒时间戳
        long currentTimeMillis = System.currentTimeMillis();
        // 调用工具方法并打印结果
        String formattedDate = timestampToDateString(currentTimeMillis, "yyyy-MM-dd HH:mm:ss");
        System.out.println(formattedDate);
    }
}