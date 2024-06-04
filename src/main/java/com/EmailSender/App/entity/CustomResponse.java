package com.EmailSender.App.entity;

import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomResponse {
    private String message;
    private HttpStatus httpStatus;
    private boolean success = false;
}
