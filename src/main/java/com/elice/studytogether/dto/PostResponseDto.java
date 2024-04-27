package com.elice.studytogether.dto;

import com.elice.studytogether.domain.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponseDto {
    private Long id;
    private String title;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
    }

    public Post toEntity() {
        Post post = new Post();
        post.setId(id);
        post.setTitle(title);
        return post;
    }
}
