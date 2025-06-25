package com.netflix.clone.video_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.netflix.clone.video_service.dto.VideoDTO;
import com.netflix.clone.video_service.exception.ResourceNotFoundException;
import com.netflix.clone.video_service.model.Video;
import com.netflix.clone.video_service.repository.VideoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoInterface {

    // Assuming you have a VideoRepository injected here
    private final VideoRepository videoRepository;

    // public VideoServiceImpl(VideoRepository videoRepository) {
    //     this.videoRepository = videoRepository;
    // }

    @Override
    public VideoDTO addVideo(VideoDTO videoDTO) {
        // Implementation for adding a video
        Video video = new Video();
        BeanUtils.copyProperties(videoDTO, video);
        Video savedVideo = videoRepository.save(video);
        videoDTO.setId(savedVideo.getId());
        return videoDTO; 
    }

    @Override
    public List<VideoDTO> getAllVideos() {
        // Implementation for getting all videos
        return videoRepository.findAll()
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList()); 
    }

    private VideoDTO toDTO(Video video) {
        VideoDTO videoDTO = new VideoDTO();
        BeanUtils.copyProperties(video, videoDTO);
        return videoDTO;
    }

    @Override
    public VideoDTO getVideoById(Long id) {
        // Implementation for getting a video by ID
        Video video = videoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Video Not Found"));
        return toDTO(video); 
    }

    @Override
    public void deleteVideo(Long id) {
        // Implementation for deleting a video by ID
        videoRepository.deleteById(id);
    }

    // @Override
    // public VideoDTO updateVideo(Long id, VideoDTO videoDTO) {
    //     // Implementation for updating a video by ID
    //     return null; // Replace with actual implementation
    // }

}
