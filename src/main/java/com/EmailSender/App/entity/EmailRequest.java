package com.EmailSender.App.entity;

import jakarta.persistence.Entity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EmailRequest {
    private String to;
    private String subject;
    private String message;
}
