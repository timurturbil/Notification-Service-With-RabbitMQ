package com.turbil.notificationService.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("notifications/")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/verify-account")
    public ResponseEntity<ResponseDto> verifyAccount(@RequestBody NotificationDTO notificationDTO){
        ResponseDto response = notificationService.verifyAccount(notificationDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/change-password")
    public ResponseEntity<ResponseDto> changePassword(@RequestBody NotificationDTO notificationDTO){
        ResponseDto response = notificationService.changePassword(notificationDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);    }

    @PostMapping("/order-received")
    public ResponseEntity<ResponseDto> orderReceived(@RequestBody NotificationDTO notificationDTO){
        ResponseDto response = notificationService.orderReceived(notificationDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);    }
}