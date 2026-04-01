package com.example.qacommunity.service;

public interface ModerationService {
    boolean checkContent(String content);
    
    String getBlockedReason();
}
