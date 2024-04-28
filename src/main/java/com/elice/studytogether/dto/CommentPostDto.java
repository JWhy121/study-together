package com.elice.studytogether.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CommentPostDto {
    private Long postId;
    private String content;
}
