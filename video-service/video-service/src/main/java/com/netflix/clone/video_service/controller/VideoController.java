package com.netflix.clone.video_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.clone.video_service.dto.VideoDTO;
import com.netflix.clone.video_service.service.VideoInterface;
import com.netflix.clone.video_service.service.VideoServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class VideoController {
    private final VideoInterface videoService;

    @PostMapping("path")
    public VideoDTO addVideo(@RequestBody VideoDTO videoDTO) {
        //TODO: process POST request
        
        return videoService.addVideo(videoDTO);
    }

    @GetMapping("path")
    public List<VideoDTO> getAllVideos() {
        return videoService.getAllVideos();
    }

    @GetMapping("/{id}")
    public VideoDTO getVideoById(@PathVariable Long id) {
        return videoService.getVideoById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteVideo(@PathVariable Long id) {
        videoService.deleteVideo(id);
    }

    
    
    

    

}
