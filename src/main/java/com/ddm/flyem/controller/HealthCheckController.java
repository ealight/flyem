package com.ddm.flyem.controller;

import com.ddm.flyem.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/healthcheck")
public class HealthCheckController {

    @Autowired
    MailService mailService;

    @GetMapping
    public String helloWorld() {
        mailService.sendMail("Test Subject", "Test Text", "ealightpawn@gmail.com");
        return "Application is up!!!";
    }
}
