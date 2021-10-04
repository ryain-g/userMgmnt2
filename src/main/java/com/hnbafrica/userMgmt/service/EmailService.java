package com.hnbafrica.userMgmt.service;


import com.hnbafrica.userMgmt.Utility.Uuid;
import com.hnbafrica.userMgmt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {
    @Autowired
     JavaMailSender emailSender;

    @Autowired
     SpringTemplateEngine templateEngine;

    @Autowired
    Uuid uuid;

    public void sendVerificationEmail(User user) throws MessagingException{

        String subject= "Verification Email";
        String verify = "http://localhost:8085/users/confirmCode/";

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        helper.setFrom("noreply@baeldung.com");
        helper.setTo(user.getEmail());
        helper.setSubject(subject);
        helper.setText("To confirm your account, please click here : "
                +verify+user.getFirstName());
        helper.setFrom("innovationhnb@gmail.com");
        emailSender.send(message);
    }
}
