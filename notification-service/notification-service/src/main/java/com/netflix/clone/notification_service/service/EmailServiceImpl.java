package com.netflix.clone.notification_service.service;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.netflix.clone.notification_service.dto.EmailRequest;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
// import lombok.Value;

@Service
public class EmailServiceImpl implements EmailService {

    // @Override
    // public void sendEmail(EmailRequest emailRequest) {
    //     // Implementation for sending a generic email
    // }

    // @Override
    // public void sendWelcomeEmail(String recipientEmail, String username) {
    //     // Implementation for sending a welcome email
    // }

    // @Override
    // public void sendSubscriptionRenewalReminder(String recipientEmail, String username, LocalDate renewalDate) {
    //     // Implementation for sending a subscription renewal reminder
    // }

    // @Override
    // public void sendNewContentNotification(String recipientEmail, String username, String contentTitle) {
    //     // Implementation for sending a new content notification
    // }

    // @Override
    // public void sendPasswordResetEmail(String recipientEmail, String resetToken) {
    //     // Implementation for sending a password reset email
    // }
    private final JavaMailSender javaMailSender;
    private final SendGrid sendGrid;
    private final boolean sendGridEnabled;
    private final EmailMetricsServices metricsService;
    
    public EmailServiceImpl(JavaMailSender javaMailSender,
                                 @Value("${sendgrid.api-key}") String sendGridApiKey,
                                 @Value("${sendgrid.enabled}") boolean sendGridEnabled,
                                 EmailMetricsServices metricsService) {
        this.javaMailSender = javaMailSender;
        this.sendGrid = new SendGrid(sendGridApiKey);
        this.sendGridEnabled = sendGridEnabled;
        this.metricsService = metricsService;
    }

    @Override
    @Async
    @Retryable(value = {MailException.class}, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public void sendEmail(EmailRequest emailRequest) {
        try {
            if (sendGridEnabled) {
                sendViaSendGrid(emailRequest);
            } else {
                sendViaJavaMailSender(emailRequest);
            }
            metricsService.recordSuccess(emailRequest.getTo(), emailRequest.getSubject());
        } catch (Exception e) {
            metricsService.recordFailure(emailRequest.getTo(), emailRequest.getSubject(), e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void sendViaSendGrid(EmailRequest emailRequest) throws IOException {
        Email from = new Email("no-reply@netflixclone.com");
        Email to = new Email(emailRequest.getTo());
        Content content = new Content(emailRequest.isHtml() ? "text/html" : "text/plain", emailRequest.getBody());
        Mail mail = new Mail(from, emailRequest.getSubject(), to, content);
        
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        
        Response response = sendGrid.api(request);
        if (response.getStatusCode() < 200 || response.getStatusCode() >= 300) {
            throw new RuntimeException("SendGrid API error: " + response.getBody());
        }
    }

    private void sendViaJavaMailSender(EmailRequest emailRequest) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        
        try {
            helper.setTo(emailRequest.getTo());
            helper.setSubject(emailRequest.getSubject());
            helper.setText(emailRequest.getBody(), emailRequest.isHtml());
            helper.setFrom("no-reply@netflixclone.com");
            
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new MailException("Failed to send email via JavaMailSender", e) {};
        }
    }

    @Override
    public void sendWelcomeEmail(String recipientEmail, String username) {
        String subject = "Welcome to Netflix Clone!";
        String body = String.format("""
            <html>
                <body>
                    <h1>Welcome, %s!</h1>
                    <p>Thank you for joining Netflix Clone. Start exploring our vast library of content.</p>
                    <a href="https://netflixclone.com/browse">Start Watching Now</a>
                </body>
            </html>
            """, username);
            
        EmailRequest request = new EmailRequest();
        request.setTo(recipientEmail);
        request.setSubject(subject);
        request.setBody(body);
        request.setHtml(true);
        
        sendEmail(request);
    }

    @Override
    public void sendSubscriptionRenewalReminder(String recipientEmail, String username, LocalDate renewalDate) {
        // TODO Auto-generated method stub
        if (sendGridEnabled) {
            String subject = "Subscription Renewal Reminder";
            String body = String.format("""
                <html>
                    <body>
                        <h1>Hi %s,</h1>
                        <p>Your subscription is set to renew on %s. Please ensure your payment details are up to date.</p>
                        <a href="https://netflixclone.com/account">Manage Subscription</a>
                    </body>
                </html>
                """, username, renewalDate.toString());
                
            EmailRequest request = new EmailRequest();
            request.setTo(recipientEmail);
            request.setSubject(subject);
            request.setBody(body);
            request.setHtml(true);
            
            sendEmail(request);
            
        }
        throw new UnsupportedOperationException("Unimplemented method 'sendSubscriptionRenewalReminder'");
    }

    @Override
    public void sendNewContentNotification(String recipientEmail, String username, String contentTitle) {
        // TODO Auto-generated method stub
        if (sendGridEnabled) {
            String subject = "New Content Available: " + contentTitle;
            String body = String.format("""
                <html>
                    <body>
                        <h1>Hi %s,</h1>
                        <p>Check out our latest content: <strong>%s</strong>.</p>
                        <a href="https://netflixclone.com/content/%s">Watch Now</a>
                    </body>
                </html>
                """, username, contentTitle, contentTitle.replace(" ", "-").toLowerCase());
                
            EmailRequest request = new EmailRequest();
            request.setTo(recipientEmail);
            request.setSubject(subject);
            request.setBody(body);
            request.setHtml(true);
            
            sendEmail(request);
            
        }
        throw new UnsupportedOperationException("Unimplemented method 'sendNewContentNotification'");
    }

    @Override
    public void sendPasswordResetEmail(String recipientEmail, String resetToken) {
        // TODO Auto-generated method stub
        if (sendGridEnabled) {
            String subject = "Password Reset Request";
            String body = String.format("""
                <html>
                    <body>
                        <h1>Password Reset Request</h1>
                        <p>To reset your password, please click the link below:</p>
                        <a href="https://netflixclone.com/reset-password?token=%s">Reset Password</a>
                        <p>If you did not request this, please ignore this email.</p>
                    </body>
                </html>
                """, resetToken);
                
            EmailRequest request = new EmailRequest();
            request.setTo(recipientEmail);
            request.setSubject(subject);
            request.setBody(body);
            request.setHtml(true);
            
            sendEmail(request);
            
        }
        throw new UnsupportedOperationException("Unimplemented method 'sendPasswordResetEmail'");
    }


}
