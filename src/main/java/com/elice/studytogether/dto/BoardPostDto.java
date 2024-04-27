package com.elice.studytogether.dto;


import com.elice.studytogether.domain.Board;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardPostDto {
    private String board_title;
    private String description;

    public Board toEntity() {
        Board board = new Board();
        board.setBoard_title(board_title);
        board.setDescription(description);

        return board;
    }

}