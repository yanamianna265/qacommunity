package com.example.qacommunity.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CommentVO {
    private Integer commentId;
    private String content;
    private Integer answerId;
    private Integer userId;
    private String username;
    private LocalDateTime createdAt;
}
