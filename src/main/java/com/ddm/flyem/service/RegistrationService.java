package com.ddm.flyem.service;

import com.ddm.flyem.dao.User;
import com.ddm.flyem.dto.RegistrationDto;

public interface RegistrationService {
    User save(RegistrationDto registrationDto);
    boolean isEmailUnique(RegistrationDto dto);
    boolean isUsernameUnique(RegistrationDto dto);
}
