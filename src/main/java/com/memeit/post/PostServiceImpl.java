package com.memeit.post;

import com.memeit.exception.PostNotFoundException;
import com.memeit.user.User;
import com.memeit.utils.UuidGenerator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @Override
    public List<PostDto> findAll() {
        List<Post> allPosts = postRepository.findAll();
        return PostMapper.mapToDto(allPosts);
    }

    @Override
    public PostDto findByUuid(String uuid) {
        Optional<Post> postOptional = postRepository.findByUuid(uuid);
        Post response = postOptional.orElseThrow(() -> new PostNotFoundException("Post with provided Uuid {" + uuid + "} doesn't exist"));
        PostDto responseDto = PostMapper.mapToDto(response);
        return responseDto;
    }

    @Override
    public PostDto save(PostDto postDto) {
        postDto.setUuid(UuidGenerator.generateUuid());
        Post savedPost = postRepository.save(PostMapper.mapToModel(postDto));
        return PostMapper.mapToDto(savedPost);

    }

    @Override
    public PostDto updateByUuid(String uuid, PostDto requestBody) {
        PostDto postDtoToUpdate = findByUuid(uuid);

        if (requestBody.getImage() != null) postDtoToUpdate.setImage(requestBody.getImage());
        if (requestBody.getAuthor() != null) postDtoToUpdate.setAuthor(requestBody.getAuthor());
        if (requestBody.getTitle() != null) postDtoToUpdate.setTitle(requestBody.getTitle());
        if (requestBody.getUploadDate() != null) postDtoToUpdate.setUploadDate(requestBody.getUploadDate());

        Post postUpdated = postRepository.saveAndFlush(PostMapper.mapToModel(postDtoToUpdate));
        PostDto responseBody = PostMapper.mapToDto(postUpdated);
        return responseBody;
    }

    @Override
    public void deleteByUuid(String uuid) {
        PostDto postToDelete = findByUuid(uuid);
        Post post = PostMapper.mapToModel(postToDelete);
        postRepository.delete(post);
    }

    @Override
    public Optional<PostDto> findByAuthor(User author) {
        Post post = postRepository.findByAuthor(author).orElse(new Post());
        PostDto responseDto = PostMapper.mapToDto(post);
        return Optional.of(responseDto);
    }
}
