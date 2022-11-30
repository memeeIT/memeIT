package com.memeit.comment;

import com.memeit.post.Post;
import com.memeit.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    private User author;

    @ManyToOne
    private Post post;

    @Column(length = 200)
    private String content;
    private int votes;

    private Date uploadDate;




}
