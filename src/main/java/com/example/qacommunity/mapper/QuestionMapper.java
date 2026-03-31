package com.example.qacommunity.mapper;

import com.example.qacommunity.entity.Question;
import com.example.qacommunity.vo.QuestionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface QuestionMapper {
    int insert(Question question);
    
    Question selectById(@Param("questionId") Integer questionId);
    
    List<QuestionVO> selectList(@Param("offset") Integer offset, @Param("limit") Integer limit);
    
    Long countAll();
    
    List<QuestionVO> selectByTag(@Param("tag") String tag, @Param("offset") Integer offset, @Param("limit") Integer limit);
    
    Long countByTag(@Param("tag") String tag);
}
