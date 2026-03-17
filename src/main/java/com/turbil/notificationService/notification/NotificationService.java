package com.turbil.notificationService.notification;

public interface NotificationService {
    ResponseDto verifyAccount(NotificationDTO notification);
    ResponseDto changePassword(NotificationDTO notification);
    ResponseDto orderReceived(NotificationDTO notification);
}

