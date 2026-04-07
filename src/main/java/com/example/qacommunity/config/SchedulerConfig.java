package com.example.qacommunity.config;

import com.example.qacommunity.service.HotRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    @Autowired
    private HotRankService hotRankService;

    @Scheduled(cron = "0 0 * * * ?")
    public void updateHotScores() {
        hotRankService.calculateAndUpdateHotScores();
    }
}
