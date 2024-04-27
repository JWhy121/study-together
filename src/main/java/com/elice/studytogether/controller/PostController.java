package com.elice.studytogether.controller;


import com.elice.studytogether.domain.Board;
import com.elice.studytogether.domain.Post;
import com.elice.studytogether.dto.BoardPostDto;
import com.elice.studytogether.dto.PostDto;
import com.elice.studytogether.dto.PostResponseDto;
import com.elice.studytogether.service.BoardService;
import com.elice.studytogether.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final BoardService boardService;
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    public PostController(PostService postService, BoardService boardService){

        this.postService = postService;
        this.boardService = boardService;
    }

    @GetMapping("/create")
    public String getPostEditView(@RequestParam("boardId") Long id, Model model){



        Board board = boardService.getBoardById(id);
        model.addAttribute("board", board);

        return "/post/createPost";
    }

    @PostMapping("/create")
    public String createPost(@RequestParam("boardId") Long id, @ModelAttribute PostDto postDto){

        logger.info("Received boardId: {}", id);
        postDto.setBoard_id(id);
        postService.savePost(postDto);

        return "redirect:/boards/" + id;
    }


}
