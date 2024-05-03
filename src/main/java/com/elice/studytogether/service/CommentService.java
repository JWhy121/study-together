package com.elice.studytogether.service;


import com.elice.studytogether.domain.Comment;
import com.elice.studytogether.domain.Post;
import com.elice.studytogether.dto.*;
import com.elice.studytogether.mapper.CommentMapper;
import com.elice.studytogether.repository.CommentRepository;
import com.elice.studytogether.repository.jdbcTemplateBoardReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper mapper;

    @Autowired
    public CommentService(CommentRepository commentRepository, CommentMapper mapper){
        this.commentRepository = commentRepository;
        this.mapper = mapper;
    }

    public List<Comment> retrieveAllComments(Long id){
        return commentRepository.findByPostId(id);
    }

    public Comment retrieveCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Comment with id " + id + "does not exist"));
    }

    public CommentResponseDto saveComment(Long postId, CommentPostDto commentPostDto){
        Comment comment = new Comment();
        Post post = new Post();
        post.setId(postId);
        comment.setPost(post);
        comment.setContent(commentPostDto.getContent());
        comment.setNickname(commentPostDto.getNickname());
        comment.setPassword(commentPostDto.getPassword());
        commentRepository.save(comment);

        CommentResponseDto commentResponseDto = mapper.commentToCommentResponseDto(comment);

        return commentResponseDto;
    }

    public CommentResponseDto updateComment(Long commentId, CommentPutDto commentPutDto){

        return commentRepository.findById(commentId)
                .map(existingComment -> {
                    //form으로부터 받아온 password
                    String passwordFromDto = commentPutDto.getPassword();

                    //기존 데이터에 있는 password 확인
                    String savedPassword = existingComment.getPassword();

                    if(!passwordFromDto.equals(savedPassword)){
                        throw new IllegalArgumentException("password is fault. please check again.");
                    }

                    existingComment.setContent(commentPutDto.getContent());
                    Comment updatedComment = commentRepository.save(existingComment);
                    return convertToDto(updatedComment);
                })
                .orElseThrow(() -> new IllegalStateException("Comment with id " + commentId + "does note exist"));
    }

    public void deleteComment(Long commentId){
        commentRepository.deleteById(commentId);
    }

    public boolean checkPassword(Long commentId, String password) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if(comment.isPresent()){
            Comment getComment = comment.get();

            return getComment.getPassword().equals(password);
        }

        return false;
    }


    private CommentResponseDto convertToDto(Comment comment) {
        return new CommentResponseDto();
    }

}
