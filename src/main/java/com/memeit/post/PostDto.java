package com.memeit.post;

import com.memeit.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PostDto {

    private Long id;

    private String image;

    private User author;

    private String title;

    private LocalDate uploadDate;

    private Set<User> votes  = new HashSet<>();

//    private List<CommentDto> comments;

}
