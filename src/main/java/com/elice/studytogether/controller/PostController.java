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
import java.util.Optional;

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

    //게시글 뷰
    @GetMapping("/{boardId}")
    public String getPostView(@PathVariable("boardId") Long id, Model model){
        PostResponseDto post = postService.retrievePostById(id);

        if(post == null) {
            // post 객체가 null인 경우, 404 에러를 반환하거나 다른 처리를 수행할 수 있습니다.
            return "error/404"; // 혹은 다른 에러 처리 방법을 선택하세요.
        } else {
            // post 객체가 null이 아닌 경우에만 모델에 추가합니다.
            model.addAttribute("post", post);
            return "post/post";
        }

    }


    //게시글 생성 뷰
    @GetMapping("/create")
    public String getPostCreateView(@RequestParam("boardId") Long id, Model model){

        Board board = boardService.getBoardById(id);
        model.addAttribute("board", board);

        return "post/createPost";
    }


    //게시글 생성
    @PostMapping("/create")
    public String createPost(@RequestParam("boardId") Long id, @ModelAttribute PostDto postDto){

        logger.info("Received boardId: {}", id);
        postDto.setBoardId(id);
        postService.savePost(postDto);

        return "redirect:/boards/" + id;
    }



}
