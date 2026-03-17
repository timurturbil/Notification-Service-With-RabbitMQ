package com.turbil.notificationService.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {
    private NotificationType type;
    private String receiver;
    private String title;
    private String body;
    private String templateName;
    private Map<String, Object> dynamicValue;
}