package com.memeit.post;

import com.memeit.comment.Comment;
import com.memeit.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
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

    @Column(name = "IMAGE", nullable = false)
    private String imageUrl;

    @ManyToOne
    @JoinColumn (name = "author_id")
    @ToString.Exclude
    private User author;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "UPLOAD_DATE", nullable = false)
    private LocalDate uploadDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "post_votes",
    joinColumns = @JoinColumn(name= "postId"),
    inverseJoinColumns = @JoinColumn(name = "userId"))
    private Set<User> votes = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private Set<Comment> comments;


    public Post addUserVote(User userVote) {
        votes.add(userVote);
        return this;
    }


}
