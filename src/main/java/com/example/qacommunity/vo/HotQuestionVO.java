package com.example.qacommunity.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class HotQuestionVO {
    private Integer questionId;
    private String title;
    private String content;
    private String tag;
    private Integer userId;
    private String username;
    private LocalDateTime createdAt;
    private BigDecimal hotScore;
    private Integer answerCount;
}
