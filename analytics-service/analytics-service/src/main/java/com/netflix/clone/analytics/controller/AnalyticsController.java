package com.netflix.clone.analytics.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.clone.analytics.model.UserWatchStat;
import com.netflix.clone.analytics.service.AnalyticsService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private AnalyticsService analyticsService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserWatchStat>> getUserAnalytics(@PathVariable String userId) {
        return ResponseEntity.ok(analyticsService.getUserStat(userId));
    }

    @GetMapping("/trending")
    public ResponseEntity<List<String>> getTrendingVideos() {
        return ResponseEntity.ok(analyticsService.getTrendingVideos());
    }
    
    
    


}
