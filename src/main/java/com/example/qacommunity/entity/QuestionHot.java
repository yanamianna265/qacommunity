package com.example.qacommunity.entity;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class QuestionHot {
    private Integer questionId;
    private BigDecimal hotScore;
    private Integer answerCount;
    private Integer viewCount;
    private Long lastUpdateTime;
}
