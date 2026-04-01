package com.example.qacommunity.controller;

import com.example.qacommunity.common.Result;
import com.example.qacommunity.service.ModerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/moderation")
public class ModerationController {

    @Autowired
    private ModerationService moderationService;

    @PostMapping("/check")
    public Result<Map<String, Object>> check(@RequestParam String content) {
        boolean passed = moderationService.checkContent(content);
        Map<String, Object> result = new HashMap<>();
        result.put("passed", passed);
        if (!passed) {
            result.put("reason", moderationService.getBlockedReason());
        }
        return Result.success(result);
    }
}
