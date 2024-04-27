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
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.boardId = post.getBoard().getId();
        this.createdDate = post.getCreatedDate();
        this.modifiedDate = post.getModifiedDate();
    }

    public Post toEntity() {
        Post post = new Post();
        post.setId(id);
        post.setTitle(title);
        post.setContent(content);

        Board board = new Board();
        board.setId(boardId);
        post.setBoard(board);

        post.setCreatedDate(createdDate);
        post.setModifiedDate(modifiedDate);
        return post;
    }
}
