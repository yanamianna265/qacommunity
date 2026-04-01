package com.example.qacommunity.service.impl;

import com.example.qacommunity.common.PageResult;
import com.example.qacommunity.entity.Comment;
import com.example.qacommunity.exception.BusinessException;
import com.example.qacommunity.mapper.AnswerMapper;
import com.example.qacommunity.mapper.CommentMapper;
import com.example.qacommunity.service.CommentService;
import com.example.qacommunity.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private AnswerMapper answerMapper;

    @Override
    public CommentVO publish(Integer answerId, String content, Integer userId) {
        if (answerMapper.selectById(answerId) == null) {
            throw new BusinessException("回答不存在");
        }
        
        Comment comment = new Comment();
        comment.setAnswerId(answerId);
        comment.setContent(content);
        comment.setUserId(userId);
        comment.setCreatedAt(LocalDateTime.now());
        
        commentMapper.insert(comment);
        
        CommentVO vo = new CommentVO();
        vo.setCommentId(comment.getCommentId());
        vo.setAnswerId(answerId);
        vo.setContent(content);
        vo.setUserId(userId);
        vo.setCreatedAt(comment.getCreatedAt());
        return vo;
    }

    @Override
    public PageResult<CommentVO> listByAnswer(Integer answerId, Integer page, Integer size) {
        int offset = (page - 1) * size;
        List<CommentVO> list = commentMapper.selectByAnswerId(answerId, offset, size);
        Long total = commentMapper.countByAnswerId(answerId);
        return PageResult.of(total, page, size, list);
    }
}
