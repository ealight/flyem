package com.ddm.flyem.convertor;

import com.ddm.flyem.dao.User;
import com.ddm.flyem.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User toEntity(UserDto dto) {

        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .build();
        return user;
    }

    public UserDto toDto(User user) {
        UserDto dto = UserDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
        return dto;
    }
}
