package com.example.qacommunity.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Comment {
    private Integer commentId;
    private String content;
    private Integer answerId;
    private Integer userId;
    private LocalDateTime createdAt;
}
