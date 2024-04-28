package com.elice.studytogether.service;


import com.elice.studytogether.domain.Post;
import com.elice.studytogether.dto.PostDto;
import com.elice.studytogether.dto.PostPutDto;
import com.elice.studytogether.dto.PostResponseDto;
import com.elice.studytogether.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final BoardService boardService;

    @Autowired
    public PostService(PostRepository postRepository, BoardService boardService) {
        this.postRepository = postRepository;
        this.boardService = boardService;
    }

    public Page<PostResponseDto> retrieveAllPosts(Long id, Pageable pageable){
        Page<Post> postPage = postRepository.findByBoardId(id, pageable);
        return postPage.map(this::convertToDto);
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

    public List<Post> searchPostByKeyword(String keyword){
        return postRepository.findByTitleContaining(keyword);
    }

    public PostResponseDto putPost(Long id, PostPutDto postPutDto){
        return postRepository.findById(id)
                .map(existingPost -> {
                    existingPost.setTitle(postPutDto.getTitle());
                    existingPost.setContent(postPutDto.getContent());
                    Post updatedPost = postRepository.save(existingPost);
                    return convertToDto(updatedPost);
                })
                .orElseThrow(() -> new IllegalStateException("Post with id " + id + "does note exist"));
    }

    public void deletePost(Long id) {postRepository.deleteById(id);}




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
