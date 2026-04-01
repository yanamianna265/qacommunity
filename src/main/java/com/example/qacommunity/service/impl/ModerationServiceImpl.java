package com.example.qacommunity.service.impl;

import com.example.qacommunity.service.ModerationService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class ModerationServiceImpl implements ModerationService {

    private static final Set<String> BLOCKED_WORDS = new HashSet<>(Arrays.asList(
            "傻逼", "白痴", "智障", "废物", "垃圾", "混蛋", "王八蛋", "滚", "蠢货",
            "fuck", "shit", "damn", "asshole", "bastard"
    ));

    private String blockedReason = null;

    @Override
    public boolean checkContent(String content) {
        blockedReason = null;
        if (content == null || content.trim().isEmpty()) {
            return true;
        }
        
        String lowerContent = content.toLowerCase();
        for (String word : BLOCKED_WORDS) {
            if (lowerContent.contains(word.toLowerCase())) {
                blockedReason = "包含敏感词: " + word;
                return false;
            }
        }
        return true;
    }

    @Override
    public String getBlockedReason() {
        return blockedReason;
    }
}
