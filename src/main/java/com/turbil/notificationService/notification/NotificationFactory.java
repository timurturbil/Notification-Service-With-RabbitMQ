package com.turbil.notificationService.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationFactory {

    private final EmailNotificationService emailNotificationService;
    // TODO: SmsNotificationService, VoiceNotificationService

    public Notification getNotificationService(NotificationType type) {
        return switch (type) {
            case EMAIL -> emailNotificationService;
            case SMS -> throw new UnsupportedOperationException("SMS not implemented yet");
            case VOICE -> throw new UnsupportedOperationException("VOICE not implemented yet");
        };
    }
}