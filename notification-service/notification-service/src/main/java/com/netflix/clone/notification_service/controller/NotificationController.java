package com.netflix.clone.notification_service.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.clone.notification_service.service.EmailServiceImpl;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    
    private final EmailServiceImpl notificationService;
    
    public NotificationController(EmailServiceImpl notificationService) {
        this.notificationService = notificationService;
    }
    
    @PostMapping("/welcome")
    public ResponseEntity<String> sendWelcomeEmail(@RequestParam String email, @RequestParam String username) {
        notificationService.sendWelcomeEmail(email, username);
        return ResponseEntity.accepted().body("Welcome email queued for sending");
    }
    
    @PostMapping("/subscription-reminder")
    public ResponseEntity<String> sendSubscriptionReminder(
            @RequestParam String email,
            @RequestParam String username,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate renewalDate) {
        
        notificationService.sendSubscriptionRenewalReminder(email, username, renewalDate);
        return ResponseEntity.accepted().body("Subscription reminder queued for sending");
    }
    
    // Other endpoints
}