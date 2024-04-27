package com.elice.studytogether.controller;


import com.elice.studytogether.domain.Board;
import com.elice.studytogether.domain.Post;
import com.elice.studytogether.dto.BoardPostDto;
import com.elice.studytogether.dto.PostResponseDto;
import com.elice.studytogether.service.BoardService;
import com.elice.studytogether.service.PostService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/boards")
public class BoardController {

    private BoardService boardService;
    private PostService postService;

    @Autowired
    public BoardController(BoardService boardService, PostService postService){
        this.boardService = boardService;
        this.postService = postService;
    }


    //게시판 메인 페이지 모든 게시판 조회
    @GetMapping
    public String getAllBoards(Model model) {
        List<Board> boards = boardService.getAllBoards();
        model.addAttribute("boards", boards);
        return "board/boards";
    }

    //게시판 생성
    @GetMapping("/create")
    public String getCreateBoard(){return "/board/createBoard";}


    @PostMapping("/create")
    public String createBoard(@ModelAttribute BoardPostDto boardPostDto){
        Board board = boardPostDto.toEntity();
        boardService.saveBoard(board);

        return "redirect:/boards";
    }

    @GetMapping("/{id}")
    public String getBoard(@PathVariable("id") Long id, Model model){

        Board board = boardService.getBoardById(id);
        model.addAttribute("board", board);

        List<PostResponseDto> posts = postService.retrieveAllPosts();
        model.addAttribute("post", posts);

        return "/board/board";
    }

}
