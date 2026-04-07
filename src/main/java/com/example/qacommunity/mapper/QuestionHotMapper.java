package com.example.qacommunity.mapper;

import com.example.qacommunity.entity.QuestionHot;
import com.example.qacommunity.vo.HotQuestionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface QuestionHotMapper {
    void insertOrUpdate(@Param("questionId") Integer questionId, @Param("hotScore") Double hotScore, 
                        @Param("answerCount") Integer answerCount, @Param("viewCount") Integer viewCount);
    
    List<HotQuestionVO> selectHotList(@Param("limit") Integer limit);
    
    QuestionHot selectByQuestionId(@Param("questionId") Integer questionId);
    
    void deleteByQuestionId(@Param("questionId") Integer questionId);
}
