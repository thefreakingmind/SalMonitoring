package com.api.monitoring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailNotificationService {

    private final JavaMailSender emailSender;

    @Autowired
    public EmailNotificationService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendErrorNotification(String subject, String message) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo("salman.siddiqui100@gmail.com");
        email.setSubject(subject);
        email.setText(message);
        emailSender.send(email);
    }
}
