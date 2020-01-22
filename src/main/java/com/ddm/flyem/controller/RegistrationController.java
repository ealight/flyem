package com.ddm.flyem.controller;

import com.ddm.flyem.dao.User;
import com.ddm.flyem.dto.RegistrationDto;
import com.ddm.flyem.service.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return registrationService.registerUser(registrationDto);
    }

    @GetMapping("/confirmEmail/{token}")
    private ResponseEntity<String> confirmEmail(@PathVariable String token) {
        boolean isEmailConfirmed = registrationService.confirmEmail(token);

        if(!isEmailConfirmed) {
            return ResponseEntity.badRequest().body("Bad token");
        }

        return ResponseEntity.ok("Email successfully confirmed!");
    }

}
