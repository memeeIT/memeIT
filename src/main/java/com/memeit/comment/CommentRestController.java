package com.memeit.comment;

import com.memeit.common.PageMappingInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PageMappingInfo.API_PATH)
public class CommentRestController {

    private final CommentServiceImpl commentService;



    public CommentRestController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @GetMapping(PageMappingInfo.COMMENTS_PER_POST_API_PATH)
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getPostComments(@PathVariable(name = "id") Long id) {
            return commentService.getPostComments(id);
    }

}
