package com.memeit.post;

import java.util.List;
import java.util.stream.Collectors;

public class PostMapper {

    static PostDto mapToDto(Post post) {
        return new PostDto().toBuilder()
                .id(post.getId())
                .imageUrl(post.getImageUrl())
                .author(post.getAuthor())
                .title(post.getTitle())
                .uploadDate(post.getUploadDate())
                .votes(post.getVotes())
                .build();
    }

    static List<PostDto> mapToDto(List<Post> posts) {
        return posts.
                stream()
                .map(PostMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public static Post mapToModel(PostDto postDto) {
        return new Post().toBuilder()
                .id(postDto.getId())
                .imageUrl(postDto.getImageUrl())
                .author(postDto.getAuthor())
                .title(postDto.getTitle())
                .uploadDate(postDto.getUploadDate())
                .votes(postDto.getVotes())
                .build();
    }

//    static List<Post> mapToModel(List<PostDto> postDtos) {
//        return postDtos
//                .stream()
//                .map(PostMapper::mapToModel)
//                .collect(Collectors.toList());
//    }

}