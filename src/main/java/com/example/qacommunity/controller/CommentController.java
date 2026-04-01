package com.example.qacommunity.controller;

import com.example.qacommunity.common.PageResult;
import com.example.qacommunity.common.Result;
import com.example.qacommunity.service.CommentService;
import com.example.qacommunity.util.JwtUtil;
import com.example.qacommunity.vo.CommentVO;
import io.jsonwebtoken.Claims;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@Validated
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public Result<CommentVO> publish(
            @RequestHeader("Authorization") String authHeader,
            @RequestParam Integer answerId,
            @RequestParam @NotBlank String content) {
        
        Claims claims = jwtUtil.parseToken(authHeader.replace("Bearer ", ""));
        Integer userId = Integer.parseInt(claims.getSubject());
        
        CommentVO vo = commentService.publish(answerId, content, userId);
        return Result.success(vo);
    }

    @GetMapping("/list/{answerId}")
    public Result<PageResult<CommentVO>> list(
            @PathVariable Integer answerId,
            @RequestParam(defaultValue = "1") @Min(1) Integer page,
            @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer size) {
        
        PageResult<CommentVO> result = commentService.listByAnswer(answerId, page, size);
        return Result.success(result);
    }
}
