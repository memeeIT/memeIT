package com.memeit.post;

import com.memeit.common.PageMappingInfo;
import com.memeit.image.FileImageServicesImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(PageMappingInfo.API_PATH)
public class PostRestController {

    private final PostServiceImpl postService;
    private FileImageServicesImpl fileImageServices;

    public PostRestController(PostServiceImpl postService, FileImageServicesImpl fileImageServices) {
        this.postService = postService;
        this.fileImageServices = fileImageServices;
    }

    @GetMapping(PageMappingInfo.POST_API_PATH)
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> getAllPosts() {
        return postService.findAll();
    }

    @GetMapping(PageMappingInfo.POST_ID_PATH)
    @ResponseStatus(HttpStatus.OK)
    public PostDto getPost(@PathVariable(name = "id") Long id) {
        return postService.findById(id);
    }

    @PostMapping(PageMappingInfo.POST_API_PATH)//Method upload Image
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto savePost(@RequestParam("image") MultipartFile file, @ModelAttribute PostDto postDto) throws IOException, IOException {
        fileImageServices.uploadImageToFileSystem(file,postDto);
        return postService.save(postDto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping(PageMappingInfo.POST_ID_PATH)
    public PostDto updatePost(@PathVariable(name = "id") Long id,
                              @RequestBody PostDto requestBody) {
        return postService.updateById(id, requestBody);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(PageMappingInfo.POST_ID_PATH)
    public void deletePost(@PathVariable(name = "id") Long id) {
        postService.deleteById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(PageMappingInfo.POST_API_PATH+"/vote"+PageMappingInfo.ID_PATH)
    public void giveLike(@PathVariable(name = "id") Long id){
        postService.voteForMeme(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(PageMappingInfo.POST_API_PATH+"/vote"+PageMappingInfo.ID_PATH)
    public PostVoteCountDto getVoteCountForPost(@PathVariable(name = "id") Long id){

        return postService.getCurrentVoteCountForPost(id);

    }

}
