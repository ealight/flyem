package com.ddm.flyem.service.impl;

import com.ddm.flyem.dao.User;
import com.ddm.flyem.dto.RegistrationDto;
import com.ddm.flyem.repository.MailTokenRepository;
import com.ddm.flyem.repository.UserRepository;
import com.ddm.flyem.service.MailService;
import com.ddm.flyem.service.RegistrationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private static final String URL_EMAIL_TOKEN_SUFFIX = "/api/register/confirmEmail/";

    private final UserRepository userRepository;
    private final MailTokenRepository mailTokenRepository;
    private final MailService mailService;

    @Value("${flyem.url.base}")
    private String urlBase;

    public RegistrationServiceImpl(UserRepository userRepository, MailTokenRepository mailTokenRepository, MailService mailService) {
        this.userRepository = userRepository;
        this.mailTokenRepository = mailTokenRepository;
        this.mailService = mailService;
    }


    @Override
    @Transactional
    public User registerUser(RegistrationDto dto) {
        String hashedPassword = new BCryptPasswordEncoder().encode(dto.getPassword());
        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(hashedPassword)
                .build();

        User savedUser = userRepository.save(user);

        String token = generateRandomToken();

        mailTokenRepository.saveTokenForUser(user, token);

        String emailText = "Please follow this link: " + generateUrlForToken(token);

        mailService.sendMail("Confirm email", emailText, savedUser.getEmail());
        return savedUser;
    }

    @Override
    public boolean isEmailUnique(RegistrationDto dto) {
        boolean isEmailExist = userRepository.existsByEmail(dto.getEmail());
        return !isEmailExist;
    }

    @Override
    public boolean isUsernameUnique(RegistrationDto dto) {
        boolean isUsernameExist = userRepository.existsByUsername(dto.getUsername());
        return !isUsernameExist;
    }

    @Override
    @Transactional
    public boolean confirmEmail(String token) {
        User user = mailTokenRepository.getUserByToken(token);

        if (user == null) {
            return false;
        }

        user.setEmailApproved(true);

        mailTokenRepository.deleteTokenForUser(user);
        userRepository.save(user);

        return true;
    }

    private String generateRandomToken() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 40;

        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);

        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }

        return buffer.toString();
    }

    private String generateUrlForToken(String token) {
        return urlBase + URL_EMAIL_TOKEN_SUFFIX + token;
    }
}
