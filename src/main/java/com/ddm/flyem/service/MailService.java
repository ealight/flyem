package com.ddm.flyem.service;

public interface MailService {
    void sendMail(String subject, String text, String... emails);
}
