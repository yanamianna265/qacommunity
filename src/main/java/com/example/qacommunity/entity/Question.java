package com.example.qacommunity.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Question {
    private Integer questionId;
    private String title;
    private String content;
    private String tag;
    private Integer userId;
    private LocalDateTime createdAt;
}
