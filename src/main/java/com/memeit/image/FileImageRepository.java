package com.memeit.image;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FileImageRepository extends JpaRepository<FileImage,Long> {

    Optional<FileImage> findByName(String fileName);

    @Override
    Optional<FileImage> findById(Long id);
}
