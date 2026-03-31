package com.example.qacommunity.mapper;

import com.example.qacommunity.entity.Answer;
import com.example.qacommunity.vo.AnswerVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface AnswerMapper {
    int insert(Answer answer);
    
    Answer selectById(@Param("answerId") Integer answerId);
    
    List<AnswerVO> selectByQuestionId(@Param("questionId") Integer questionId, @Param("offset") Integer offset, @Param("limit") Integer limit);
    
    Long countByQuestionId(@Param("questionId") Integer questionId);
}
