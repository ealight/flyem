package com.ddm.flyem.service.impl;

import com.ddm.flyem.dao.User;
import com.ddm.flyem.dto.RegistrationDto;
import com.ddm.flyem.repository.UserRepository;
import com.ddm.flyem.service.RegistrationService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private UserRepository userRepository;

    public RegistrationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User save(RegistrationDto dto) {
        String hashedPassword = new BCryptPasswordEncoder().encode(dto.getPassword());
        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(hashedPassword)
                .build();
        return userRepository.save(user);
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
}
