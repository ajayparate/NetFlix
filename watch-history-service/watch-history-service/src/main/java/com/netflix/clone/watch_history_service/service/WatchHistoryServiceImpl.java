package com.netflix.clone.watch_history_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.netflix.clone.watch_history_service.dto.WatchHistoryDTO;
import com.netflix.clone.watch_history_service.exception.ResourceNotFoundException;
import com.netflix.clone.watch_history_service.model.WatchHistory;
import com.netflix.clone.watch_history_service.repository.WatchHistoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WatchHistoryServiceImpl implements WatchHistoryInterface {

    private final WatchHistoryRepository watchHistoryRepository;



    @Override
    public WatchHistoryDTO saveOrUpdate(WatchHistoryDTO watchHistoryDTO) {
        // TODO Auto-generated method stub
        WatchHistory watchHistory = watchHistoryRepository.findByUserIdAndVideoId(watchHistoryDTO.getUserId(), watchHistoryDTO.getVideoId())
                .orElse(new WatchHistory());
        // throw new UnsupportedOperationException("Unimplemented method 'saveOrUpdate'");
        watchHistory.setUserId(watchHistoryDTO.getUserId());
        watchHistory.setVideoId(watchHistoryDTO.getVideoId());
        watchHistory.setProgressSeconds(watchHistoryDTO.getProgressSeconds());
        watchHistory.setCompleted(watchHistoryDTO.getCompleted());
        watchHistory.setLastWatched(watchHistoryDTO.getLastWatched());
        return toDTO(watchHistoryRepository.save(watchHistory));
    }

    // private WatchHistoryDTO toDTO(WatchHistory watchHistory) {
    //     return WatchHistoryDTO.builder()
    //             .id(watchHistory.getId())
    //             .userId(watchHistory.getUserId())
    //             .videoId(watchHistory.getVideoId())
    //             .progressSeconds(watchHistory.getProgressSeconds())
    //             .completed(watchHistory.getCompleted())
    //             .lastWatched(watchHistory.getLastWatched())
    //             .build();
    // }

    private WatchHistoryDTO toDTO(WatchHistory watchHistory) {
        
        WatchHistoryDTO watchHistoryDTO = new WatchHistoryDTO();
        BeanUtils.copyProperties(watchHistory, watchHistoryDTO);
        return watchHistoryDTO;
    }



    @Override
    public List<WatchHistoryDTO> getAllByUserId(Long userId) {
        return watchHistoryRepository.findByUserId(userId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        // throw new UnsupportedOperationException("Unimplemented method 'getAllByUserId'");

    }

    @Override
    public WatchHistoryDTO getByUserIdAndVideoId(Long userId, Long videoId) {
        // TODO Auto-generated method stub
        WatchHistory watchHistory = watchHistoryRepository.findByUserIdAndVideoId(userId, videoId)
                .orElseThrow(()-> new ResourceNotFoundException("Watch history not found for userId: " + userId + " and videoId: " + videoId));
        // throw new UnsupportedOperationException("Unimplemented method 'getByUserIdAndVideoId'");
        return toDTO(watchHistory);
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        watchHistoryRepository.deleteById(id);
        // throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

}
