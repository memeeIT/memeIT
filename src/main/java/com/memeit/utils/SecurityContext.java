package com.memeit.utils;

import com.memeit.user.User;
import com.memeit.user.UserDto;
import com.memeit.user.UserMapper;
import com.memeit.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityContext {

    private UserService userService;

    @Autowired
    public SecurityContext(UserService userService) {
        this.userService = userService;
    }

    public User getCurrentLoggedInUser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDto user = userService.findByUsername(name).orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.mapToModel(user);
    }


    public Long getCurrentLoggedInUserId() {
        return getCurrentLoggedInUser().getId();
    }
}
