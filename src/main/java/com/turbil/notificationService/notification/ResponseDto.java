package com.turbil.notificationService.notification;

import lombok.Data;

@Data
public class ResponseDto {
    private String status;
    private int statusCode;
    private String message;
}
