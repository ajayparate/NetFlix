package com.netflix.clone.watch_history_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.clone.watch_history_service.dto.WatchHistoryDTO;
// import com.netflix.clone.watch_history_service.model.WatchHistory;
import com.netflix.clone.watch_history_service.service.WatchHistoryServiceImpl;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/v1/watch-history")
@RequiredArgsConstructor
public class WatchHistoryController {

    private final WatchHistoryServiceImpl watchHistoryService;

    @PostMapping("path/to/post")
    public WatchHistoryDTO saveOrUpdateDto(@RequestBody WatchHistoryDTO dto) {
        //TODO: process POST request
        
        return watchHistoryService.saveOrUpdate(dto);
    }

    @GetMapping("path/to/get")
    public List<WatchHistoryDTO> getUserHistoryDTOs(@PathVariable Long userId) {
        return watchHistoryService.getAllByUserId(userId);
    }

    @GetMapping("path")
    public WatchHistoryDTO getUserVideoHistory(@PathVariable Long userId, @PathVariable Long videoId) {
        return watchHistoryService.getByUserIdAndVideoId(userId, videoId);
    }

    @DeleteMapping("/{id}")
    public void deleteWatchHistory(@PathVariable Long id) {
        watchHistoryService.deleteById(id);
    }
    

}
