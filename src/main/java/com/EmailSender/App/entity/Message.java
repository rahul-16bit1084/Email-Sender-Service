package com.EmailSender.App.entity;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String from;
    private String content;
    private List<String> files;
    private String subjects;
}
