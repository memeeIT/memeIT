package com.memeit.user;

import com.memeit.exception.ResourceNotFoundException;
import com.memeit.post.Post;
import com.memeit.post.PostDto;
import com.memeit.utils.UuidGenerator;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDto> findAll() {
        List<User> allUsers = userRepository.findAll();
        return UserMapper.mapToDto(allUsers);
    }

    @Override
    public UserDto findByUuid(String uuid) {
        Optional<User> userOptional = userRepository.findByUuid(uuid);
        User response = userOptional.orElseThrow(() -> new ResourceNotFoundException("User", "UUID", uuid));
        UserDto responseDto = UserMapper.mapToDto(response);
        return responseDto;
    }

    @Override
    public UserDto save(UserDto userDto) {
        userDto.setUuid(UuidGenerator.generateUuid());
        userDto.setRole(Role.USER);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User savedUser = userRepository.save(UserMapper.mapToModel(userDto));
        return UserMapper.mapToDto(savedUser);
    }

    @Override
    public UserDto updateByUuid(String uuid, UserDto requestBody) {
        UserDto userDtoToUpdate = findByUuid(uuid);
        userDtoToUpdate.setPassword(passwordEncoder.encode(requestBody.getPassword()));

        if (requestBody.getFirstName() != null) userDtoToUpdate.setFirstName(requestBody.getFirstName());
        if (requestBody.getEmail() != null) userDtoToUpdate.setEmail(requestBody.getEmail());
        if (requestBody.getLastName() != null) userDtoToUpdate.setLastName(requestBody.getLastName());
        if (requestBody.getUsername() != null) userDtoToUpdate.setUsername(requestBody.getUsername());

        User userUpdated = userRepository.saveAndFlush(UserMapper.mapToModel(userDtoToUpdate));
        UserDto responseBody = UserMapper.mapToDto(userUpdated);
        return responseBody;
    }

    @Override
    public void deleteByUuid(String uuid) {
        UserDto userToDelete = findByUuid(uuid);
        User user = UserMapper.mapToModel(userToDelete);
        userRepository.delete(user);
    }

    @Override
    public Optional<UserDto> findByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(new User());
        UserDto responseDto = UserMapper.mapToDto(user);
        return Optional.of(responseDto);
    }

    @Override
    public User addPost(Post savedPost) {
        User currentUser = getCurrentUser();
        User user = currentUser.addPost(savedPost);
        return userRepository.saveAndFlush(user);
    }

    public User getCurrentUser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserDto> userDto = findByUsername(name);
        return UserMapper.mapToModel(userDto.get());
    }
}
