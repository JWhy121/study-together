package com.elice.studytogether.service;


import com.elice.studytogether.domain.Post;
import com.elice.studytogether.dto.BoardPostDto;
import com.elice.studytogether.dto.BoardResponseDto;
import com.elice.studytogether.dto.PostDto;
import com.elice.studytogether.dto.PostResponseDto;
import com.elice.studytogether.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final BoardService boardService;

    @Autowired
    public PostService(PostRepository postRepository, BoardService boardService) {
        this.postRepository = postRepository;
        this.boardService = boardService;
    }

    public List<PostResponseDto> retrieveAllPosts(){
        return ((List<Post>) postRepository.findAll()).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public PostResponseDto retrievePostById(Long id) {
        return postRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new IllegalStateException("Post with id " + id + "does not exist"));
    }

    public PostResponseDto savePost(PostDto postDto) {
        Post post = new Post();
        post.setBoard(postDto.toEntity().getBoard());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        Post savedPost = postRepository.save(post);
        return convertToDto(savedPost);
    }

//    public PostResponseDto savePost(PostDto postDto) {
//        Post post = postDto.toEntity();
//        if (postDto.getBoard_id() != null) {
//            BoardResponseDto board = boardService.retrieveBoardById(postDto.getBoard_id());
//            post.setBoard(board.toEntity());
//        }
//        return new PostResponseDto(postRepository.save(post));
//    }



    private PostResponseDto convertToDto(Post post) {
        return new PostResponseDto(post);
    }
}
