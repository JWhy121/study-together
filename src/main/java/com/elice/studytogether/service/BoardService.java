package com.elice.studytogether.service;


import com.elice.studytogether.domain.Board;
import com.elice.studytogether.dto.BoardPostDto;
import com.elice.studytogether.dto.BoardResponseDto;
import com.elice.studytogether.repository.jdbcTemplateBoardReposiroty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final jdbcTemplateBoardReposiroty boardReposiroty;

    public BoardService(jdbcTemplateBoardReposiroty boardRepository){
        this.boardReposiroty = boardRepository;
    }

    public List<Board> getAllBoards() {
        return boardReposiroty.findAll();
    }

    public Board getBoardById(Long id) {
        return boardReposiroty.findById(id).orElse(null);
    }

    public BoardResponseDto retrieveBoardById(Long id) {
        return boardReposiroty.findById(id)
                .map(BoardResponseDto::new)
                .orElseThrow(() -> new IllegalStateException("Skill with id " + id + " does not exist"));
    }

    public Long saveBoard(Board board) {
        Board result = boardReposiroty.save(board);
        return result.getId();
    }

    public void updateBoard(Board board) {
        boardReposiroty.update(board);
    }

    public void deleteBoardById(Long id) {
        boardReposiroty.deleteById(id);
    }

}
