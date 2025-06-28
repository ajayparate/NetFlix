package com.netflix.clone.notification_service.service;

import java.time.LocalDate;

import com.netflix.clone.notification_service.dto.EmailRequest;

public interface EmailService {

    void sendEmail(EmailRequest emailRequest);
    void sendWelcomeEmail(String recipientEmail, String username);
    void sendSubscriptionRenewalReminder(String recipientEmail, String username, LocalDate renewalDate);
    void sendNewContentNotification(String recipientEmail, String username, String contentTitle);
    void sendPasswordResetEmail(String recipientEmail, String resetToken);
}
