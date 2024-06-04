package com.EmailSender.App.service;

import com.EmailSender.App.entity.Message;
import jakarta.mail.Folder;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    private JavaMailSender mailSender;
    @Override
    public void sendEmail(String to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom("senderemail@gmail.com");
        mailSender.send(simpleMailMessage);
//        logger.info("Email has been sent.");
    }
    @Override
    public void sendEmail(String[] to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom("senderemail@gmail.com");
        mailSender.send(simpleMailMessage);
    }

    @Override
    public void sendEmailWithHTML(String to, String subject, String htmlContent) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try{
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setFrom("senderemail@gmail.com");
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(htmlContent, true);

            mailSender.send(mimeMessage);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendEmailWithFile(String to, String subject, String message, File file) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try{
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setFrom("senderemail@gmail.com");
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(message);

            FileSystemResource fileSystemResource = new FileSystemResource(file);
            mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), file);

            mailSender.send(mimeMessage);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendEmailWithFileInputStream(String to, String subject, String message, InputStream inputStream) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try{
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setFrom("senderemail@gmail.com");
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(message,true);

            File file = new File("src/main/resources/static/Images/test.png");
            Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            FileSystemResource fileSystemResource = new FileSystemResource(file);

            mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), file);

            mailSender.send(mimeMessage);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @Value("${mail.store.protocol}")
    String protocol;
    @Value("${mail.imaps.host}")
    String host;
    @Value("${mail.imaps.port}")
    String port;
    @Value("${spring.mail.username}")
    String username;
    @Value("${spring.mail.password}")
    String password;
    @Override
    public List<Message> getInboxMessage() {
        // Message Recieving Code

        Properties properties = new Properties();
        properties.setProperty("mail.store.protocol",protocol);
        properties.setProperty("mail.imaps.host",host);
        properties.setProperty("mail.imaps.port",port);

        Session session = Session.getDefaultInstance(properties);

        try{
            Store store = session.getStore();
            store.connect(username,password);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            jakarta.mail.Message[] messages = inbox.getMessages();
            for(jakarta.mail.Message message : messages){
                System.out.println(message.getSubject());
                System.out.println("------------------------------");
            }
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

        return null;
    }
}
