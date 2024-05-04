package com.elice.studytogether.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CommentPostDto {
    private String content;
    private String nickname;
    private String password;
}
