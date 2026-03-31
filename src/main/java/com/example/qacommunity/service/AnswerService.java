package com.example.qacommunity.service;

import com.example.qacommunity.common.PageResult;
import com.example.qacommunity.vo.AnswerVO;

public interface AnswerService {
    AnswerVO publish(Integer questionId, String content, Integer userId);
    
    PageResult<AnswerVO> listByQuestion(Integer questionId, Integer page, Integer size);
}
