package com.elice.studytogether.dto;

import com.elice.studytogether.domain.Board;
import com.elice.studytogether.domain.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostPutDto {
    private String title;
    private String content;
    private Long boardId;
    private String devLang;
    private String nickname;
    private String password;
    private boolean completed;

    public Post toEntity(){
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);

        Board board = new Board();
        board.setId(boardId);

        post.setBoard(board);
        post.setDevLang(devLang);
        post.setNickname(nickname);
        post.setPassword(password);
        post.setCompleted(completed);


        return post;
    }


}
