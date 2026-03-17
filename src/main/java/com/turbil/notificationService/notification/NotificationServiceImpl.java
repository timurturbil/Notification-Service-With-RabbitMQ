package com.turbil.notificationService.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.email.name}")
    private String emailExchange;

    @Value("${rabbitmq.binding.email.name}")
    private String emailRoutingKey;

    @Override
    public ResponseDto verifyAccount(NotificationDTO notification) {
        ResponseDto responseDTO = new ResponseDto();
        try {
            rabbitTemplate.convertAndSend(emailExchange, emailRoutingKey, NotificationDTO.builder()
                .receiver(notification.getReceiver())
                .title(notification.getTitle())
                    .body(notification.getBody())
                    .dynamicValue(notification.getDynamicValue())
                    .type(NotificationType.EMAIL)
                    .templateName("verification")
                    .build());

            return responseDTO;
        } catch (Exception e) {
            return responseDTO;
        }
    }

    @Override
    public ResponseDto changePassword(NotificationDTO notification) {
        ResponseDto responseDTO = new ResponseDto();
        try {
            return responseDTO;
        } catch (Exception e) {
            return responseDTO;
        }
    }

    @Override
    public ResponseDto orderReceived(NotificationDTO notification) {
        ResponseDto responseDTO = new ResponseDto();
        try {
            return responseDTO;
        } catch (Exception e) {
            return responseDTO;
        }
    }
}

