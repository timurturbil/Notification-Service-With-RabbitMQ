package com.turbil.notificationService.notification;

import jakarta.mail.MessagingException;

public interface Notification {
    void send(NotificationDTO dto) throws MessagingException;
}