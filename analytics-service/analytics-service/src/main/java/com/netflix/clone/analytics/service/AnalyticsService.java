package com.netflix.clone.analytics.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.netflix.clone.analytics.model.UserWatchStat;
import com.netflix.clone.analytics.repository.UserWatcchStatRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final UserWatcchStatRepository userWatchStatRepository;

    public List<UserWatchStat> getUserStat(String userId){
        return userWatchStatRepository.findByUserId(userId);
    }


    public List<String> getTrendingVideos(){
        return userWatchStatRepository.findTrendingVideos()
                                    .stream()
                                    .map(record -> (String) record[0])
                                    .collect(Collectors.toList());
    }
}
