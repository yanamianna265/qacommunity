package com.example.qacommunity.service;

import com.example.qacommunity.common.PageResult;
import com.example.qacommunity.vo.QuestionVO;

public interface QuestionService {
    QuestionVO publish(String title, String content, String tag, Integer userId);
    
    PageResult<QuestionVO> list(Integer page, Integer size);
    
    QuestionVO getById(Integer questionId);
    
    PageResult<QuestionVO> getByTag(String tag, Integer page, Integer size);
}
