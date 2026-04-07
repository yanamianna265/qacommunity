package com.example.qacommunity.controller;

import com.example.qacommunity.common.Result;
import com.example.qacommunity.service.HotRankService;
import com.example.qacommunity.vo.HotQuestionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rank")
public class HotRankController {

    @Autowired
    private HotRankService hotRankService;

    @GetMapping("/hot")
    public Result<List<HotQuestionVO>> getHotList(
            @RequestParam(defaultValue = "20") Integer limit) {
        List<HotQuestionVO> list = hotRankService.getHotList(limit);
        return Result.success(list);
    }

    @PostMapping("/refresh")
    public Result<Void> refreshHotScores() {
        hotRankService.calculateAndUpdateHotScores();
        return Result.success();
    }
}
