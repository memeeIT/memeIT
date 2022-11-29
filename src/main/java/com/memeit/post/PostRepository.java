package com.memeit.post;

import com.memeit.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Override
    List<Post> findAll();

    Optional<Post> findById(Long id);

    Optional<Post> findByAuthor(User author);

}
