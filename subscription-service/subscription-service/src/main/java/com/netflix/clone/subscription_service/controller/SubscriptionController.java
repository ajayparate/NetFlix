package com.netflix.clone.subscription_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.clone.subscription_service.dto.SubscriptionDTO;
import com.netflix.clone.subscription_service.service.SubscriptionServiceImpl;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionServiceImpl service;

    @PostMapping("path/to/post")
    public SubscriptionDTO subscribe(@PathVariable Long id, @PathVariable Long planId) {
        return service.subscribe(id, planId);
    }

    @GetMapping("path/to/get")
    public SubscriptionDTO getUserSubscription(@PathVariable Long userId) {
        return service.getSubscription(userId);
    }

    @GetMapping("/plans")
    public List<SubscriptionDTO> getAllPlans() {
        return service.getAllPlans();
    }
    
    
    
}
