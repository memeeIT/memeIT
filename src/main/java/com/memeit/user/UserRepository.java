package com.memeit.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    List<User> findAll();

    Optional<User> findByUuid(String uuid);

    Optional<User> findByUsername(String username);
}
