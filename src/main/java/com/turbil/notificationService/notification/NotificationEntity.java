package com.turbil.notificationService.notification;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "notifications")
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private NotificationType type;

    private String receiver;

    private String title;

    private String body;

    private String templateName;

    private String status;
}
