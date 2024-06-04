package com.EmailSender.App.Controller;

import com.EmailSender.App.entity.CustomResponse;
import com.EmailSender.App.entity.EmailRequest;
import com.EmailSender.App.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<CustomResponse> sendEmail(@RequestBody EmailRequest emailRequest){
        emailService.sendEmailWithHTML(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getMessage());

        return ResponseEntity.ok(
                CustomResponse.builder().message("Email Send Successfully !!!").httpStatus(HttpStatus.OK)
                        .success(true).build()
        );
    }
    @PostMapping("/send-with-file")
    public ResponseEntity<CustomResponse> sendEmailWithFile(@RequestPart EmailRequest emailRequest, @RequestPart MultipartFile multipartFile) throws IOException {
        emailService.sendEmailWithFileInputStream(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getMessage(), multipartFile.getInputStream());

        return ResponseEntity.ok(
                CustomResponse.builder().message("Email Send Successfully !!!").httpStatus(HttpStatus.OK)
                        .success(true).build()
        );
    }
    @Scheduled(fixedDelay = 60000)
    public void scheduleEmail(){
        EmailRequest emailRequest = new EmailRequest("xyz@gmail.com", "ABC..Subject.", "hi there . This is the message");
        emailService.sendEmailWithHTML(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getMessage());
        System.out.println("Email Send");
    }
}
