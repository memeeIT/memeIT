package com.memeit.user;

import java.util.List;
import java.util.Optional;

public interface UserService {


    List<UserDto> findAll();

    UserDto findByUuid(String uuid);

    UserDto save(UserDto userDto);

    UserDto updateByUuid(String uuid, UserDto requestBody);

    void deleteByUuid(String uuid);

    Optional<UserDto> findByUsername(String username);
}
