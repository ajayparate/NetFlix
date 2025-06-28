package com.netflix.clone.watch_history_service.service;

import java.util.List;

import com.netflix.clone.watch_history_service.dto.WatchHistoryDTO;

public interface WatchHistoryInterface {

    WatchHistoryDTO saveOrUpdate(WatchHistoryDTO watchHistoryDTO);

    List<WatchHistoryDTO> getAllByUserId(Long userId);

    WatchHistoryDTO getByUserIdAndVideoId(Long userId, Long videoId);

    void deleteById(Long id);
}
