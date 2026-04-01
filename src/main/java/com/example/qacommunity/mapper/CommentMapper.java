package com.example.qacommunity.mapper;

import com.example.qacommunity.entity.Comment;
import com.example.qacommunity.vo.CommentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface CommentMapper {
    int insert(Comment comment);
    
    List<CommentVO> selectByAnswerId(@Param("answerId") Integer answerId, @Param("offset") Integer offset, @Param("limit") Integer limit);
    
    Long countByAnswerId(@Param("answerId") Integer answerId);
}
