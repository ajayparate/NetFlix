package com.netflix.clone.watch_history_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netflix.clone.watch_history_service.model.WatchHistory;

public interface WatchHistoryRepository extends JpaRepository<WatchHistory, Long> {
    // Custom query methods can be defined here if needed

    List<WatchHistory> findByUserId(Long userId);
    Optional<WatchHistory> findByUserIdAndVideoId(Long userId, Long videoId);

}
