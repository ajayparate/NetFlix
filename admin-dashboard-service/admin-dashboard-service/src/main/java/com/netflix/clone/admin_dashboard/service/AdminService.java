package com.netflix.clone.admin_dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.clone.admin_dashboard.model.AdminVideoRequest;

@Service
public class AdminService {

    @Autowired
    private VideoClient videoClient;

    public void addVideo(AdminVideoRequest videoRequest) {
        videoClient.addVideo(videoRequest);
    }

    public void removeVideo(String videoId) {
        videoClient.deleteVideo(videoId);
    }
}
