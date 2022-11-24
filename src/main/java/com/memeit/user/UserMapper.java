package com.memeit.user;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {


    static UserDto mapToDto(User user) {
        return new UserDto().toBuilder()
                .id(user.getId())
                .uuid(user.getUuid())
                .password(user.getPassword())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .build();
    }

    static List<UserDto> mapToDto(List<User> users) {
        return users.
                stream()
                .map(UserMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public static User mapToModel(UserDto userDto) {
        return new User().toBuilder()
                .id(userDto.getId())
                .uuid(userDto.getUuid())
                .password(userDto.getPassword())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .role(userDto.getRole())
                .build();
    }

    static List<User> mapToModel(List<UserDto> userDtos) {
        return userDtos
                .stream()
                .map(UserMapper::mapToModel)
                .collect(Collectors.toList());
    }
}
