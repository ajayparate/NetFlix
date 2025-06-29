package com.netflix.clone.admin_dashboard.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.netflix.clone.admin_dashboard.model.AdminVideoRequest;

@FeignClient(name = "video-service")
public interface VideoClient {

    @PostMapping("/api/videos")
    void addVideo(@RequestBody AdminVideoRequest video);

    @DeleteMapping("/api/videos/{id}")
    void deleteVideo(@PathVariable("id") String videoId);


}
