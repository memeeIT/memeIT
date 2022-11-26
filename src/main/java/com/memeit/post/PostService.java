package com.memeit.post;

import com.memeit.user.User;

import java.util.List;
import java.util.Optional;

public interface PostService {

    List<PostDto> findAll();

    PostDto findByUuid(String uuid);

    PostDto save(PostDto postDto);

    PostDto updateByUuid(String uuid, PostDto requestBody);

    void deleteByUuid(String uuid);

    Optional<PostDto> findByAuthor(User author);


}
