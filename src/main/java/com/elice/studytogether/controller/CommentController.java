package com.elice.studytogether.controller;


import com.elice.studytogether.domain.Board;
import com.elice.studytogether.domain.Comment;
import com.elice.studytogether.dto.BoardPostDto;
import com.elice.studytogether.dto.CommentPostDto;
import com.elice.studytogether.dto.CommentPutDto;
import com.elice.studytogether.dto.CommentResponseDto;
import com.elice.studytogether.mapper.CommentMapper;
import com.elice.studytogether.service.BoardService;
import com.elice.studytogether.service.CommentService;
import com.elice.studytogether.service.PostService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper mapper;

    @Autowired
    public CommentController(CommentService commentService, CommentMapper mapper){

        this.commentService = commentService;
        this.mapper = mapper;
    }

    @PostMapping
    public String putComments(@RequestParam("postId") Long postId, CommentPostDto commentPostDto){
        commentService.saveComment(postId, commentPostDto);

        return "redirect:/posts/" + postId;
    }

    @PostMapping("/{commentId}/edit")
    public String updateBoard(@PathVariable("commentId") Long commentId, @ModelAttribute CommentPutDto commentPutDto){

        Comment comment = commentService.retrieveCommentById(commentId);

        commentService.updateComment(commentId, commentPutDto);

        return "redirect:/posts/" + comment.getPost().getId();
    }


    //게시판 삭제
//    @DeleteMapping("/{id}/delete")
//    public String deleteBoard(@PathVariable("id") Long id){
//        System.out.println(id);
//        boardService.deleteBoardById(id);
//        return "redirect:/boards";
//    }

}
