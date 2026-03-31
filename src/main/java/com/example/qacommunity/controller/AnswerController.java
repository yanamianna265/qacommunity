package com.example.qacommunity.controller;

import com.example.qacommunity.common.PageResult;
import com.example.qacommunity.common.Result;
import com.example.qacommunity.service.AnswerService;
import com.example.qacommunity.util.JwtUtil;
import com.example.qacommunity.vo.AnswerVO;
import io.jsonwebtoken.Claims;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/answer")
@Validated
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public Result<AnswerVO> publish(
            @RequestHeader("Authorization") String authHeader,
            @RequestParam Integer questionId,
            @RequestParam @NotBlank String content) {
        
        Claims claims = jwtUtil.parseToken(authHeader.replace("Bearer ", ""));
        Integer userId = Integer.parseInt(claims.getSubject());
        
        AnswerVO vo = answerService.publish(questionId, content, userId);
        return Result.success(vo);
    }

    @GetMapping("/list/{questionId}")
    public Result<PageResult<AnswerVO>> list(
            @PathVariable Integer questionId,
            @RequestParam(defaultValue = "1") @Min(1) Integer page,
            @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer size) {
        
        PageResult<AnswerVO> result = answerService.listByQuestion(questionId, page, size);
        return Result.success(result);
    }
}
