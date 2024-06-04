package com.EmailSender.App.service;

import com.EmailSender.App.entity.Message;

import java.io.File;
import java.io.InputStream;
import java.util.List;

// used Interface :-> in future if change to bs implementation change ho not the code
public interface EmailService {
    // mail to single person
    void sendEmail(String to, String subject, String message);
    // mail to multiple person
    void sendEmail(String[] to, String subject, String message);
    // mail to single person
    void sendEmailWithHTML(String to, String subject, String htmlContent);
    // mail to single person
    void sendEmailWithFile(String to, String subject, String message, File file);
    void sendEmailWithFileInputStream(String to, String subject, String message, InputStream inputStream);
    List<Message> getInboxMessage();
}
