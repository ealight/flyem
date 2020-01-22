package com.ddm.flyem.controller;

import com.ddm.flyem.dao.User;
import com.ddm.flyem.dto.RegistrationDto;
import com.ddm.flyem.service.RegistrationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {
    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    private User register(RegistrationDto registrationDto) {
        if (!registrationService.isEmailUnique(registrationDto)) {
            throw new IllegalArgumentException("Email already used!");
        }
        if (!registrationService.isUsernameUnique(registrationDto)) {
            throw new IllegalArgumentException("Username already used!");
        }
        return registrationService.save(registrationDto);
    }

}
