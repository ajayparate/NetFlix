package com.netflix.clone.video_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netflix.clone.video_service.model.Video;

public interface VideoRepository extends JpaRepository<Video, Long>{

 

}
