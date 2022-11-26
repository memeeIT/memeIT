package com.memeit.post;

import com.memeit.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PostDto {

    private Long id;

    private String uuid;

    private String image;

    private User author;

    private String title;

    private Date uploadDate;

    private int votes;

//    private List<CommentDto> comments;

}
