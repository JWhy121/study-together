package com.elice.studytogether.dto;


import com.elice.studytogether.domain.Board;
import com.elice.studytogether.domain.Post;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardPostDto {
    private String boardTitle;
    private String description;

    public Board toEntity() {
        Board board = new Board();
        board.setBoardTitle(boardTitle);
        board.setDescription(description);

        return board;
    }

}