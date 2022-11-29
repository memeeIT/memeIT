package com.memeit.comment;

import com.memeit.post.Post;
import com.memeit.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder(toBuilder = true)
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long commentId;

    @ManyToOne
    private User author;

    @ManyToOne
    private Post post;

    @Column(length = 200)
    private String content;

    private LocalDate uploadDate;




}
