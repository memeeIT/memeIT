package com.memeit.post;

import com.memeit.comment.Comment;
import com.memeit.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "UUID")
    private String uuid;

    @Column(name = "IMAGE", nullable = false)
    private String image;

    @ManyToOne
    @JoinColumn (name = "author_id")
    private User author;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "UPLOAD_DATE", nullable = false)
    private LocalDate uploadDate;

    @Column(name = "VOTES")
    private int votes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private Set<Comment> comments;



}
