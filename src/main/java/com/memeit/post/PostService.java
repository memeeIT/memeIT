package com.memeit.post;

import com.memeit.user.User;

import java.util.List;
import java.util.Optional;

public interface PostService {

    List<PostDto> findAll();

    PostDto findById(Long id);

    PostDto save(PostDto postDto);

    PostDto updateById(Long id, PostDto requestBody);

    void deleteById(Long id);

    Optional<PostDto> findByAuthor(User author);

    void voteForMeme(Long postId);

    PostVoteCountDto getCurrentVoteCountForPost(Long id);
}
