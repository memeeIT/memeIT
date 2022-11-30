package com.memeit.post;

import com.memeit.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Override
    List<Post> findAll();

    Optional<Post> findById(Long id);

    Optional<Post> findByAuthor(User author);
}
