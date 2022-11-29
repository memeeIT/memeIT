package com.memeit.comment;

import java.util.List;

public interface CommentService {

    List<CommentDto> getPostComments(Long id);

    CommentDto findById(Long commentId);

    CommentDto saveComment(CommentDto commentDto);

    CommentDto updateCommentById(Long commentId, CommentDto requestBody);

    void deleteCommentById(Long commentId);


    CommentDto mapToDto(Comment comment);

    Comment mapToModel(CommentDto commentDto);

}
