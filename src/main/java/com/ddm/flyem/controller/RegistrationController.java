package com.ddm.flyem.controller;

import com.ddm.flyem.dao.User;
import com.ddm.flyem.dto.RegistrationDto;
import com.ddm.flyem.service.RegistrationService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {
    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    private User register(@RequestBody RegistrationDto registrationDto) {
        if(!registrationService.isEmailUnique(registrationDto)) {
            throw new IllegalArgumentException("Email already used!");
        }
        if(!registrationService.isUsernameUnique(registrationDto)) {
            throw new IllegalArgumentException("Username already used!");
        }
        return registrationService.save(registrationDto);
    }

}
