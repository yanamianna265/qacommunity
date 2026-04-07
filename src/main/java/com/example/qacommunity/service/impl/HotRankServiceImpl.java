package com.example.qacommunity.service.impl;

import com.example.qacommunity.entity.Question;
import com.example.qacommunity.mapper.QuestionHotMapper;
import com.example.qacommunity.mapper.QuestionMapper;
import com.example.qacommunity.service.HotRankService;
import com.example.qacommunity.vo.HotQuestionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class HotRankServiceImpl implements HotRankService {

    private static final double ANSWER_WEIGHT = 0.4;
    private static final double COMMENT_WEIGHT = 0.3;
    private static final double TIME_DECAY_FACTOR = 0.1;

    @Autowired
    private QuestionHotMapper questionHotMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public void calculateAndUpdateHotScores() {
        List<HotQuestionVO> hotList = questionHotMapper.selectHotList(1000);
        
        for (HotQuestionVO hotQuestion : hotList) {
            Double hotScore = calculateHotScore(hotQuestion.getQuestionId());
            questionHotMapper.insertOrUpdate(hotQuestion.getQuestionId(), hotScore, hotQuestion.getAnswerCount(), 0);
        }
    }

    @Override
    public Double calculateHotScore(Integer questionId) {
        Question question = questionMapper.selectById(questionId);
        if (question == null) {
            return 0.0;
        }
        
        Long answerCount = questionMapper.countAll();
        
        long now = System.currentTimeMillis();
        long created = question.getCreatedAt().toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        double hoursPassed = (now - created) / (1000.0 * 60 * 60);
        if (hoursPassed < 1) hoursPassed = 1;
        
        double timeDecay = Math.log(hoursPassed + 1) * TIME_DECAY_FACTOR;
        
        double hotScore = answerCount * ANSWER_WEIGHT + timeDecay;
        
        return BigDecimal.valueOf(hotScore).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    @Override
    public List<HotQuestionVO> getHotList(Integer limit) {
        return questionHotMapper.selectHotList(limit);
    }
}
