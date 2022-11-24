package com.memeit.authentication;


import com.memeit.common.PageMappingInfo;
import com.memeit.user.User;
import com.memeit.user.UserDto;
import com.memeit.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthenticationRestController {

    private final AuthenticationService authenticationService;

    private final UserService userService;

    public AuthenticationRestController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping(PageMappingInfo.REGISTER_PAGE)
    public ResponseEntity<?> register(@Valid @RequestBody UserDto userDto) {

        Optional<UserDto> userDtoOptional = userService.findByUsername(userDto.getUsername());
        if (userDtoOptional.get().getUsername() != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.save(userDto), HttpStatus.CREATED);
    }

    @PostMapping(PageMappingInfo.LOGIN_PAGE)
    public ResponseEntity<?> login(@RequestBody User user) {
        return new ResponseEntity<>(authenticationService.signInAndReturnJWT(user), HttpStatus.OK);
    }
}
