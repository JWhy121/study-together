package com.elice.studytogether.dto;

import com.elice.studytogether.domain.Board;
import com.elice.studytogether.domain.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardResponseDto {
    private String board_title;
    private String description;

    public BoardResponseDto(Board board) {
        this.board_title = board.getBoard_title();
        this.description = board.getDescription();
    }

    public Board toEntity() {
        Board board = new Board();
        board.setBoard_title(board_title);
        board.setDescription(description);
        return board;
    }
}
