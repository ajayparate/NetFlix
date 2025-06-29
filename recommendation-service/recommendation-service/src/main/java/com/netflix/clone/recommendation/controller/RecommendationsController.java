package com.netflix.clone.recommendation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.clone.recommendation.dto.VideoDTO;
import com.netflix.clone.recommendation.service.RecommendationService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
public class RecommendationsController {

    private final RecommendationService recommendationService;

    @GetMapping("/{userId}")
    public List<VideoDTO> getRecommendations(@PathVariable Long userId) {
        return recommendationService.getRecommendations(userId);
    }
    
}
