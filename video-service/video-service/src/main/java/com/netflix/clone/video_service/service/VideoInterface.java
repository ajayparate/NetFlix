package com.netflix.clone.video_service.service;

import java.util.List;

import com.netflix.clone.video_service.dto.VideoDTO;

public interface VideoInterface {

    VideoDTO addVideo(VideoDTO videoDTO);
    List<VideoDTO> getAllVideos();
    VideoDTO getVideoById(Long id);
    void deleteVideo(Long id);
    VideoDTO updateVideo(Long id, VideoDTO videoDTO);

}
