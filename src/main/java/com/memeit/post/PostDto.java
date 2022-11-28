package com.memeit.post;

import com.memeit.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PostDto {

    private Long id;

    private String imageUrl;

    private User author;

    private String title;

    private LocalDate uploadDate;

    private int votes;

//    private List<CommentDto> comments;

}
