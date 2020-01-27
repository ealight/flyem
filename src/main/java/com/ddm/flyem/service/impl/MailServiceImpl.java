package com.ddm.flyem.service.impl;

import com.ddm.flyem.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private  JavaMailSender javaMailSender;

    @Override
    public void sendMail(String subject, String text, String... emails) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(emails);

        msg.setSubject(subject);
        msg.setText(text);

        javaMailSender.send(msg);
    }

}
