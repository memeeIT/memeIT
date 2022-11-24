package com.memeit.authentication;


import com.memeit.security.UserPrinciple;
import com.memeit.security.jwt.JwtProvider;
import com.memeit.user.Role;
import com.memeit.user.User;
import com.memeit.user.UserDto;
import com.memeit.user.UserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtProvider jwtProvider;


    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, UserService userService,
                                     JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public User signInAndReturnJWT(User signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
        );

        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        String jwt = jwtProvider.generateToken(userPrinciple);

        User signInUser = userPrinciple.getUser();
        signInUser.setToken(jwt);
        System.out.println(signInUser);
        return signInUser;
    }

    @Override
    public boolean isLoggedUserIsAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = auth.getName();
        Optional<UserDto> userDtoOptional = userService.findByUsername(currentUserName);
        if (userDtoOptional.isPresent()) {
            UserDto currentUser = userDtoOptional.get();
            return currentUser.getRole().equals(Role.ADMIN);
        } else {
            return false;
        }
    }

    @Override
    public boolean isUserLogged() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken);
    }
}
