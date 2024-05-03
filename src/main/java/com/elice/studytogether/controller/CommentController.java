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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper mapper;

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        // 비밀번호가 일치하지 않을 때 발생한 예외를 처리하여 클라이언트에게 알림
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @Autowired
    public CommentController(CommentService commentService, CommentMapper mapper) {

        this.commentService = commentService;
        this.mapper = mapper;
    }

    @PostMapping
    public String saveComments(@RequestParam("postId") Long postId, CommentPostDto commentPostDto) {
        commentService.saveComment(postId, commentPostDto);

        return "redirect:/posts/" + postId;
    }

    //댓글 수정
    @PostMapping("/{commentId}/edit")
    public ResponseEntity<?> updateComment(@PathVariable("commentId") Long commentId, @ModelAttribute CommentPutDto commentPutDto) {
        Comment comment = commentService.retrieveCommentById(commentId);
        try {
            CommentResponseDto responseDto = commentService.updateComment(commentId, commentPutDto);

            return ResponseEntity.status(HttpStatus.OK)
                    .header(HttpHeaders.LOCATION, "/posts/" + comment.getPost().getId())
                    .body(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("비밀번호가 틀렸습니다. 다시 확인해주세요.");
        }
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") Long commentId, @RequestBody Map<String, String> requestBody){
        String password = requestBody.get("password");
        boolean isPasswordCorrect = commentService.checkPassword(commentId, password);
        if(isPasswordCorrect) {
            commentService.deleteComment(commentId);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


}
