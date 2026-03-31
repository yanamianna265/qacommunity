package com.example.qacommunity.controller;

import com.example.qacommunity.common.PageResult;
import com.example.qacommunity.common.Result;
import com.example.qacommunity.service.QuestionService;
import com.example.qacommunity.util.JwtUtil;
import com.example.qacommunity.vo.QuestionVO;
import io.jsonwebtoken.Claims;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/question")
@Validated
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public Result<QuestionVO> publish(
            @RequestHeader("Authorization") String authHeader,
            @RequestParam @NotBlank String title,
            @RequestParam @NotBlank String content,
            @RequestParam(required = false) String tag) {
        
        Claims claims = jwtUtil.parseToken(authHeader.replace("Bearer ", ""));
        Integer userId = Integer.parseInt(claims.getSubject());
        
        QuestionVO vo = questionService.publish(title, content, tag, userId);
        return Result.success(vo);
    }

    @GetMapping("/list")
    public Result<PageResult<QuestionVO>> list(
            @RequestParam(defaultValue = "1") @Min(1) Integer page,
            @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer size) {
        
        PageResult<QuestionVO> result = questionService.list(page, size);
        return Result.success(result);
    }

    @GetMapping("/{id}")
    public Result<QuestionVO> getById(@PathVariable Integer id) {
        QuestionVO vo = questionService.getById(id);
        return Result.success(vo);
    }

    @GetMapping("/tag/{tag}")
    public Result<PageResult<QuestionVO>> getByTag(
            @PathVariable String tag,
            @RequestParam(defaultValue = "1") @Min(1) Integer page,
            @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer size) {
        
        PageResult<QuestionVO> result = questionService.getByTag(tag, page, size);
        return Result.success(result);
    }
}
