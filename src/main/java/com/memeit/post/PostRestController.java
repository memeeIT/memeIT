package com.memeit.post;

import com.memeit.common.PageMappingInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PageMappingInfo.API_PATH)
public class PostRestController {

    private final PostServiceImpl postService;

    public PostRestController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @GetMapping(PageMappingInfo.POST_API_PATH)
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> getAllPosts() {
        return postService.findAll();
    }

    @GetMapping(PageMappingInfo.POST_UUID_PATH)
    @ResponseStatus(HttpStatus.OK)
    public PostDto getPost(@PathVariable(name = "id") Long id) {
        return postService.findById(id);
    }

    @PostMapping(PageMappingInfo.POST_API_PATH)
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto savePost(@RequestBody PostDto postDto) {
        return postService.save(postDto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping(PageMappingInfo.POST_UUID_PATH)
    public PostDto updatePost(@PathVariable(name = "id") Long id,
                              @RequestBody PostDto requestBody) {
        return postService.updateById(id, requestBody);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(PageMappingInfo.POST_UUID_PATH)
    public void deletePost(@PathVariable(name = "id") Long id) {
        postService.deleteById(id);
    }

}
