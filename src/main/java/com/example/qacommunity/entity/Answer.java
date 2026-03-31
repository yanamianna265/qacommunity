package com.example.qacommunity.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Answer {
    private Integer answerId;
    private String content;
    private Integer questionId;
    private Integer userId;
    private LocalDateTime createdAt;
}
