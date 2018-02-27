package com.arvest.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    TemplateEngine templateEngine;

    public void sendEmail(String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("Vinod.arichella@gmail.com");
        message.setSubject("Portfolio Performance");
        message.setText(content);
        javaMailSender.send(message);
    }

    public void sendHtmlEmail(Map map, String template, String subject) throws Exception {
        final Context context = new Context(Locale.ENGLISH);
        context.setVariables(map);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
        messageHelper.setTo("Vinod.arichella@gmail.com");
        messageHelper.setSubject(subject);

        final String htmlContent = templateEngine.process(template, context);
        messageHelper.setText(htmlContent);

        javaMailSender.send(mimeMessage);
    }
}
