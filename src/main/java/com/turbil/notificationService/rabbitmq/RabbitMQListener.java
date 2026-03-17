package com.turbil.notificationService.rabbitmq;

import com.turbil.notificationService.notification.*;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMQListener {

    private final NotificationFactory notificationFactory;
    private final NotificationRepository notificationRepository;

    @RabbitListener(queues = "email_queue")
    public void processNotification(NotificationDTO dto) {
        try {
            NotificationEntity notificationEntity = new NotificationEntity();
            notificationEntity.setType(dto.getType());
            notificationEntity.setBody(dto.getBody());
            notificationEntity.setTitle(dto.getTitle());
            notificationEntity.setReceiver(dto.getReceiver());
            notificationEntity.setTemplateName(dto.getTemplateName());

            notificationEntity.setStatus("PENDING");
            notificationRepository.save(notificationEntity);

            Notification service = notificationFactory.getNotificationService(dto.getType());
            service.send(dto);

            notificationEntity.setStatus("SENT");
            notificationRepository.save(notificationEntity);
            log.info("Notification sent successfully: type={}, to={}", dto.getType(), dto.getReceiver());
        } catch (MessagingException e) {
            log.error("Failed to send notification: type={}, to={}", dto.getType(), dto.getReceiver(), e);
            // Retry logic eklenebilir
        } catch (Exception e) {
            log.error("Unexpected error in notification processing", e);
        }
    }
}