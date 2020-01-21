package com.ddm.flyem.controller;

import com.ddm.flyem.convertor.UserConverter;
import com.ddm.flyem.domain.FlyemUserDetails;
import com.ddm.flyem.dto.UserDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserConverter userConverter;

    public UserController(UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    @GetMapping
    public UserDto getCurrentUser() {
        FlyemUserDetails userWrapper = (FlyemUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userConverter.toDto(userWrapper.getUser());
    }
}
