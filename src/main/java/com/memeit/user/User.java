package com.memeit.user;

import com.memeit.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder(toBuilder = true)
@Entity
@AllArgsConstructor
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"USERNAME"}),
        @UniqueConstraint(columnNames = {"EMAIL"})})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "UUID")
    private String uuid;

    @Column(name = "USERNAME", nullable = false)
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters")
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    @Size(min = 8, message = "Password must contain at least 8 characters")
    private String password;

    @Column(name = "EMAIL", nullable = false)
    @Email(message = "Email should be valid")
    private String email;

    @Column(name = "FIRST_NAME", nullable = false)
    @Size(min = 3, max = 20, message = "First name length must be between 3 and 20 characters")
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    @Size(min = 3, max = 20, message = "Last name length must be between 3 and 20 characters")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private Role role;

//    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
//    mappedBy = "author", fetch = FetchType.LAZY)
//    private List<Post> post;

    @Transient
    private String token;

    public User() {
    }
}
