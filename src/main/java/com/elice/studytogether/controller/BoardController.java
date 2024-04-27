package com.elice.studytogether.controller;


import com.elice.studytogether.domain.Board;
import com.elice.studytogether.domain.Post;
import com.elice.studytogether.dto.BoardPostDto;
import com.elice.studytogether.dto.BoardResponseDto;
import com.elice.studytogether.dto.PostDto;
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

    //모든 게시판 뷰
    @GetMapping
    public String getAllBoardsView(Model model) {
        List<Board> boards = boardService.getAllBoards();
        model.addAttribute("boards", boards);
        return "board/boards";
    }


    //게시판 뷰
    @GetMapping("/{id}")
    public String getBoardView(@PathVariable("id") Long id,@RequestParam(name = "keyword",required = false) String keyword, Model model){

        Board board = boardService.getBoardById(id);



        if (keyword != null && !keyword.isEmpty()) {
            List<Post> ContainingkeywordPosts = postService.searchPostByKeyword(keyword);
            model.addAttribute("postPage", ContainingkeywordPosts);

        }else {
            List<PostResponseDto> posts = postService.retrieveAllPosts(id);
            model.addAttribute("postPage", posts);
        }

        model.addAttribute("board", board);



        return "board/board";
    }

    //게시판 생성 뷰
    @GetMapping("/create")
    public String getCreateBoardView(){return "/board/createBoard";}



    //게시판 수정 뷰
    @GetMapping("/{boardId}/edit")
    public String updateBoardView(@PathVariable("boardId") Long id, Model model){
        BoardResponseDto boardResponseDto = boardService.retrieveBoardById(id);
        Board board = boardResponseDto.toEntity();
        board.setId(id);
        model.addAttribute("board", board);
        return "board/editBoard";
    }


    //게시판 생성
    @PostMapping("/create")
    public String createBoard(@ModelAttribute BoardPostDto boardPostDto){
        Board board = boardPostDto.toEntity();
        boardService.saveBoard(board);

        return "redirect:/boards";
    }

    //게시판 수정
    @PostMapping("/{boardId}/edit")
    public String updateBoard(@PathVariable("boardId") Long id, @ModelAttribute BoardPostDto boardPostDto){
        Board board = boardPostDto.toEntity();
        board.setId(id);
        boardService.updateBoard(board);
        return "redirect:/boards";
    }


    //게시판 삭제
    @DeleteMapping("/{id}/delete")
    public String deleteBoard(@PathVariable("id") Long id){
        System.out.println(id);
        boardService.deleteBoardById(id);
        return "redirect:/boards";
    }

}
