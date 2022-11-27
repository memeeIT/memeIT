package com.memeit.post;

import com.memeit.exception.ResourceNotFoundException;
import com.memeit.user.User;
import com.memeit.user.UserDto;
import com.memeit.user.UserMapper;
import com.memeit.user.UserService;
import com.memeit.utils.UuidGenerator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final UserService userService;

    public PostServiceImpl(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }


    @Override
    public List<PostDto> findAll() {
        List<Post> allPosts = postRepository.findAll();
        return PostMapper.mapToDto(allPosts);
    }

    @Override
    public PostDto findByUuid(String uuid) {
        Optional<Post> postOptional = postRepository.findByUuid(uuid);
        Post response = postOptional.orElseThrow(() -> new ResourceNotFoundException("Post", "UUID", uuid));
        PostDto responseDto = PostMapper.mapToDto(response);
        return responseDto;
    }

    @Override
    public PostDto save(PostDto postDto) {
        postDto.setUuid(UuidGenerator.generateUuid());
        postDto.setUploadDate(LocalDate.now());
        postDto.setVotes(0);

        User currentUser = userService.getCurrentUser();
        postDto.setAuthor(currentUser);
        Post post = PostMapper.mapToModel(postDto);
        Post savedPost = postRepository.save(post);
        userService.addPost(savedPost);
        PostDto savedPostDto = PostMapper.mapToDto(savedPost);
        return savedPostDto;
    }

    @Override
    public PostDto updateByUuid(String uuid, PostDto requestBody) {
        PostDto postDtoToUpdate = findByUuid(uuid);
        if (requestBody.getTitle() != null) postDtoToUpdate.setTitle(requestBody.getTitle());
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
