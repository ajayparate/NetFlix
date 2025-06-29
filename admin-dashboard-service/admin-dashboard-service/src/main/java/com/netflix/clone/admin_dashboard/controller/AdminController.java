package com.netflix.clone.admin_dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.clone.admin_dashboard.model.AdminVideoRequest;
import com.netflix.clone.admin_dashboard.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/video")
    public ResponseEntity<String> addVideo(@RequestBody AdminVideoRequest videoRequest) {
        adminService.addVideo(videoRequest);
        return ResponseEntity.ok("Video added successfully.");
    }

    @DeleteMapping("/video/{id}")
    public ResponseEntity<String> deleteVideo(@PathVariable String id) {
        adminService.removeVideo(id);
        return ResponseEntity.ok("Video deleted successfully.");
    }

}
