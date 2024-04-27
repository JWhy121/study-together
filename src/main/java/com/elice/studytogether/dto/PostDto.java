package com.elice.studytogether.dto;


import com.elice.studytogether.domain.Board;
import com.elice.studytogether.domain.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {
    private String title;
    private String content;
    private Long board_id;

    public Post toEntity(){
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);

        Board board = new Board();
        board.setId(board_id);

        post.setBoard(board);


        return post;
    }


}
