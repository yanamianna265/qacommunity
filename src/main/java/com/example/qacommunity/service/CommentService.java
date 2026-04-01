package com.example.qacommunity.service;

import com.example.qacommunity.common.PageResult;
import com.example.qacommunity.vo.CommentVO;

public interface CommentService {
    CommentVO publish(Integer answerId, String content, Integer userId);
    
    PageResult<CommentVO> listByAnswer(Integer answerId, Integer page, Integer size);
}
