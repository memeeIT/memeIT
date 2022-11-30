package com.memeit.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.memeit.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserDto {


    private Long id;
    private String uuid;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
    @JsonIgnore
    private List<Post> posts;


    public UserDto() {

    }
}
