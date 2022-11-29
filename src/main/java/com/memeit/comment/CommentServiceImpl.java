package com.memeit.comment;

import com.memeit.exception.ResourceNotFoundException;
import com.memeit.post.Post;
import com.memeit.post.PostRepository;
import com.memeit.post.PostService;
import com.memeit.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{

    private final PostRepository postRepository;

    private final CommentRepository commentRepository;

    private final UserService userService;

    private final PostService postService;



    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository, UserService userService, PostService postService) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;


        this.userService = userService;
        this.postService = postService;
    }


    @Override
    public List<CommentDto> getPostComments(Long id) {

        Post post = postRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Post doesn't exist"));
        return post.getComments()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

    }

    @Override
    public CommentDto findById(Long commentId) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        Comment response = commentOptional.orElseThrow(() -> new ResourceNotFoundException("Post", "ID", commentId));
        CommentDto responseDto = mapToDto(response);
        return responseDto;

    }


    @Override
    public CommentDto saveComment(CommentDto commentDto) {


        return null;
    }

    @Override
    public CommentDto updateCommentById(Long id, CommentDto requestBody) {
        return null;
    }

    @Override
    public void deleteCommentById(Long commentId) {
    CommentDto commentToDelete = findById(commentId);
    Comment comment = mapToModel(commentToDelete);
    commentRepository.delete(comment);
    }

    @Override
    public CommentDto mapToDto(Comment comment) {
        return new  CommentDto().toBuilder()
                .commentId(comment.getCommentId())
                .content(comment.getContent())
                .author(comment.getAuthor())
                .uploadDate(comment.getUploadDate())
                .build();

    }

    @Override
    public Comment mapToModel(CommentDto commentDto) {
        return new Comment().toBuilder()
                .commentId(commentDto.getCommentId())
                .author(commentDto.getAuthor())
                .content(commentDto.getContent())
                .uploadDate(commentDto.getUploadDate())
                .build();
    }
}
