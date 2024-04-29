package com.elice.studytogether.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CommentResponseDto {
    private Long id;
    private Long postId;
    private String content;
    private String nickname;
    private String password;

}