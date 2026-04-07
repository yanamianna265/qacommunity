package com.example.qacommunity.service;

import com.example.qacommunity.vo.HotQuestionVO;
import java.util.List;

public interface HotRankService {
    void calculateAndUpdateHotScores();
    
    List<HotQuestionVO> getHotList(Integer limit);
    
    Double calculateHotScore(Integer questionId);
}
