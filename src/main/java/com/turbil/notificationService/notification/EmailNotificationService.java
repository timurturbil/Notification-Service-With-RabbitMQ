package com.turbil.notificationService.notification;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailNotificationService implements Notification {

    private final JavaMailSender emailSender;

    @Value("${application.mail.sent.from}")
    private String fromUsr;

    @Override
    public void send(NotificationDTO dto) throws MessagingException {
        String to = dto.getReceiver();
        String subject = dto.getTitle() != null ? dto.getTitle() : "Notification";
        String body = dto.getBody() != null ? dto.getBody() : generateBody(dto);

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(to);
        helper.setFrom(fromUsr);
        helper.setSubject(subject);
        helper.setText(body, true);

        emailSender.send(message);
    }

    private String generateBody(NotificationDTO dto) {
        if (dto.getTemplateName() != null) {
            String body = loadEmailTemplate(dto.getTemplateName());
            if (dto.getDynamicValue() != null) {
                for (Map.Entry<String, Object> entry : dto.getDynamicValue().entrySet()) {
                    body = body.replace("{{" + entry.getKey() + "}}", entry.getValue().toString());
                }
            }
            return body;
        }else{
            return "";
        }
    }

    public String loadEmailTemplate(String templateName) {
        ClassPathResource resource = new ClassPathResource("templates/" + templateName + ".html");
        try {
            return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Error loading email template " + templateName, e);
        }
    }
}
