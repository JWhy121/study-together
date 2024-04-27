package com.elice.studytogether.dto;

import com.elice.studytogether.domain.Board;
import com.elice.studytogether.domain.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardResponseDto {
    private String boardTitle;
    private String description;

    public BoardResponseDto(Board board) {
        this.boardTitle = board.getBoardTitle();
        this.description = board.getDescription();
    }

    public Board toEntity() {
        Board board = new Board();
        board.setBoardTitle(boardTitle);
        board.setDescription(description);
        return board;
    }
}
