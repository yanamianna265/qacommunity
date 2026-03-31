package com.example.qacommunity.service.impl;

import com.example.qacommunity.common.PageResult;
import com.example.qacommunity.entity.Question;
import com.example.qacommunity.exception.BusinessException;
import com.example.qacommunity.mapper.QuestionMapper;
import com.example.qacommunity.service.QuestionService;
import com.example.qacommunity.vo.QuestionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public QuestionVO publish(String title, String content, String tag, Integer userId) {
        Question question = new Question();
        question.setTitle(title);
        question.setContent(content);
        question.setTag(tag);
        question.setUserId(userId);
        question.setCreatedAt(LocalDateTime.now());
        
        questionMapper.insert(question);
        
        QuestionVO vo = new QuestionVO();
        vo.setQuestionId(question.getQuestionId());
        vo.setTitle(title);
        vo.setContent(content);
        vo.setTag(tag);
        vo.setUserId(userId);
        vo.setCreatedAt(question.getCreatedAt());
        return vo;
    }

    @Override
    public PageResult<QuestionVO> list(Integer page, Integer size) {
        int offset = (page - 1) * size;
        List<QuestionVO> list = questionMapper.selectList(offset, size);
        Long total = questionMapper.countAll();
        return PageResult.of(total, page, size, list);
    }

    @Override
    public QuestionVO getById(Integer questionId) {
        Question question = questionMapper.selectById(questionId);
        if (question == null) {
            throw new BusinessException("问题不存在");
        }
        QuestionVO vo = new QuestionVO();
        vo.setQuestionId(question.getQuestionId());
        vo.setTitle(question.getTitle());
        vo.setContent(question.getContent());
        vo.setTag(question.getTag());
        vo.setUserId(question.getUserId());
        vo.setCreatedAt(question.getCreatedAt());
        return vo;
    }

    @Override
    public PageResult<QuestionVO> getByTag(String tag, Integer page, Integer size) {
        int offset = (page - 1) * size;
        List<QuestionVO> list = questionMapper.selectByTag(tag, offset, size);
        Long total = questionMapper.countByTag(tag);
        return PageResult.of(total, page, size, list);
    }
}
