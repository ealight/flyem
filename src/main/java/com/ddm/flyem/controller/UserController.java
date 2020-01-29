package com.ddm.flyem.controller;

import com.ddm.flyem.converter.UserConverter;
import com.ddm.flyem.dao.Chat;
import com.ddm.flyem.dao.User;
import com.ddm.flyem.domain.FlyemUserDetails;
import com.ddm.flyem.dto.UserDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserConverter userConverter;

    public UserController(UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    @GetMapping("/current")
    public UserDto getCurrentUser() {
        FlyemUserDetails userWrapper = (FlyemUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userConverter.toDto(userWrapper.getUser());
    }

    @GetMapping("/current/chat/token")
    public List<String> getUserChatsToken() {
        FlyemUserDetails userDetails = (FlyemUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();

        List<String> result = user.getChats().stream()
                .map(Chat::getToken)
                .collect(Collectors.toList());
        return result;
    }
}
