package com.netflix.clone.analytics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.netflix.clone.analytics.model.UserWatchStat;

public interface UserWatcchStatRepository extends JpaRepository<UserWatchStat, Long> {
    
    // Custom query methods can be defined here if needed
    // For example, to find all watch stats for a specific user:
    // List<UserWatchStat> findByUserId(String userId);
    
    // Or to find all watch stats for a specific video:
    // List<UserWatchStat> findByVideoId(String videoId);
    
    // You can also add methods to filter by date, watch time, etc.
    @Query("SELECT videoId, COUNT(videoID) as views FROM UserWatchStat GROUP BY videoId ORDER BY views DESC")
    List<Object[]> findTrendingVideos();

    List<UserWatchStat> findByUserId(String userId);

}
