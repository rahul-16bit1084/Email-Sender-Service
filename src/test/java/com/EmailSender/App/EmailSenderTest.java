package com.EmailSender.App;

import com.EmailSender.App.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
@SpringBootTest

public class EmailSenderTest {
    @Autowired
    private EmailService emailService;
    @Test
    void emailSendTest(){
        System.out.println("Email Sending Test");
        emailService.sendEmail("harshadatayade99@gmail.com", "Test kr ra", "Hi There");
    }
    @Test
    void emailSendWithHTMLTest(){
        String htmlContent = "<h1 style='color:Red; border:1px'>Aunty Ji Project kr lo ji<h1>";
        emailService.sendEmailWithHTML("harshadatayade99@gmail.com", "HTML content hai Chakku", htmlContent);
    }
    @Test
    void emailSendFileTest(){
        emailService.sendEmailWithFile("harshadatayade99@gmail.com", "File wala Email","See your cute Photo",
                new File("C:\\Users\\v-rmahajan\\Downloads\\Email\\Email\\src\\main\\resources\\static\\Images\\babyChimp.jpg"));
    }
    @Test
    void emailSendFileInputStreamTest() throws FileNotFoundException {
        File file = new File("C:\\Users\\v-rmahajan\\Downloads\\Email\\Email\\src\\main\\resources\\static\\Images\\babyChimp.jpg");
        InputStream inputStream = new FileInputStream(file);

        emailService.sendEmailWithFileInputStream("harshadatayade99@gmail.com", "File wala Email","See your cute Photo",
                inputStream);
    }
    @Test
    void getInbox(){
        emailService.getInboxMessage();
    }
}
