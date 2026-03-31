package com.example.qacommunity.service.impl;

import com.example.qacommunity.common.PageResult;
import com.example.qacommunity.entity.Answer;
import com.example.qacommunity.exception.BusinessException;
import com.example.qacommunity.mapper.AnswerMapper;
import com.example.qacommunity.mapper.QuestionMapper;
import com.example.qacommunity.service.AnswerService;
import com.example.qacommunity.vo.AnswerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerMapper answerMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public AnswerVO publish(Integer questionId, String content, Integer userId) {
        if (questionMapper.selectById(questionId) == null) {
            throw new BusinessException("问题不存在");
        }
        
        Answer answer = new Answer();
        answer.setQuestionId(questionId);
        answer.setContent(content);
        answer.setUserId(userId);
        answer.setCreatedAt(LocalDateTime.now());
        
        answerMapper.insert(answer);
        
        AnswerVO vo = new AnswerVO();
        vo.setAnswerId(answer.getAnswerId());
        vo.setQuestionId(questionId);
        vo.setContent(content);
        vo.setUserId(userId);
        vo.setCreatedAt(answer.getCreatedAt());
        return vo;
    }

    @Override
    public PageResult<AnswerVO> listByQuestion(Integer questionId, Integer page, Integer size) {
        int offset = (page - 1) * size;
        List<AnswerVO> list = answerMapper.selectByQuestionId(questionId, offset, size);
        Long total = answerMapper.countByQuestionId(questionId);
        return PageResult.of(total, page, size, list);
    }
}
