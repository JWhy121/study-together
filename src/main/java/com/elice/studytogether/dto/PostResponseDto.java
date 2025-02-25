package com.elice.studytogether.dto;

import com.elice.studytogether.domain.Board;
import com.elice.studytogether.domain.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private Long boardId;
    private String nickname;
    private String devLang;
    private boolean completed;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.boardId = post.getBoard().getId();
        this.createdDate = post.getCreatedDate();
        this.modifiedDate = post.getModifiedDate();
        this.nickname = post.getNickname();
        this.devLang = post.getDevLang();
        this.completed = post.isCompleted();
    }

    public Post toEntity() {
        Post post = new Post();
        post.setId(id);
        post.setTitle(title);
        post.setContent(content);
        post.setNickname(nickname);
        post.setDevLang(devLang);

        Board board = new Board();
        board.setId(boardId);
        post.setBoard(board);
        post.setCompleted(completed);

        post.setCreatedDate(createdDate);
        post.setModifiedDate(modifiedDate);
        return post;
    }
}
