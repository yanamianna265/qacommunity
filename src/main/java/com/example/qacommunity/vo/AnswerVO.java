package com.example.qacommunity.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AnswerVO {
    private Integer answerId;
    private String content;
    private Integer questionId;
    private Integer userId;
    private String username;
    private LocalDateTime createdAt;
}
