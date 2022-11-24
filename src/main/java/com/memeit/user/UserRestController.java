package com.memeit.user;

import com.memeit.common.PageMappingInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PageMappingInfo.API_PATH)
public class UserRestController {

    private final UserServiceImpl userService;

    public UserRestController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(PageMappingInfo.USER_API_PATH)
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping(PageMappingInfo.USER_UUID_PATH)
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUser(@PathVariable(name = "uuid") String uuid) {
        return userService.findByUuid(uuid);
    }

    @PostMapping(PageMappingInfo.USER_API_PATH)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto saveUser(@RequestBody UserDto userDto) {
        return userService.save(userDto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping(PageMappingInfo.USER_UUID_PATH)
    public UserDto updateUser(@PathVariable(name = "uuid") String uuid,
                              @RequestBody UserDto requestBody) {
        return userService.updateByUuid(uuid, requestBody);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(PageMappingInfo.USER_UUID_PATH)
    public void deleteUser(@PathVariable(name = "uuid") String uuid) {
        userService.deleteByUuid(uuid);
    }
}
