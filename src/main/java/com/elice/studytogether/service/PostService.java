package com.elice.studytogether.service;


import com.elice.studytogether.domain.Post;
import com.elice.studytogether.dto.PostDto;
import com.elice.studytogether.dto.PostPutDto;
import com.elice.studytogether.dto.PostResponseDto;
import com.elice.studytogether.mapper.PostMapper;
import com.elice.studytogether.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final BoardService boardService;
    private final PostMapper postMapper;

    @Autowired
    public PostService(PostRepository postRepository, BoardService boardService, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.boardService = boardService;
        this.postMapper = postMapper;
    }

    public Page<PostResponseDto> retrieveAllPosts(Long id, Pageable pageable){

        Sort sort = Sort.by(Sort.Direction.ASC, "completed").and(Sort.by(Sort.Direction.DESC, "createdDate"));
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<Post> postPage = postRepository.findByBoardId(id, pageable);

        return postPage.map(this::convertToDto);
    }

    public PostResponseDto retrievePostById(Long id) {
        if(true){
            log.info("문제 발생! 롤백이 실행됨.");
        }
        return postRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new IllegalStateException("Post with id " + id + "does not exist"));
    }

    public PostResponseDto savePost(PostDto postDto) {

        Post post = new Post();
        post.setBoard(postDto.toEntity().getBoard());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDevLang(postDto.getDevLang());
        post.setNickname(postDto.getNickname());
        post.setPassword(postDto.getPassword());
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

    public Page<Post> searchPostByKeyword(String keyword, Pageable pageable){
        return postRepository.findByTitleContaining(keyword, pageable);
    }

    public PostResponseDto putPost(Long id, PostPutDto postPutDto){
        return postRepository.findById(id)
                .map(existingPost -> {
                    existingPost.setTitle(postPutDto.getTitle());
                    existingPost.setContent(postPutDto.getContent());
                    existingPost.setDevLang(postPutDto.getDevLang());
                    existingPost.setNickname(postPutDto.getNickname());
                    existingPost.setCompleted(postPutDto.isCompleted());
                    Post updatedPost = postRepository.save(existingPost);
                    return convertToDto(updatedPost);
                })
                .orElseThrow(() -> new IllegalStateException("Post with id " + id + "does note exist"));
    }


    @Transactional
    public void deletePost(Long id) {postRepository.deleteById(id);}



    private PostResponseDto convertToDto(Post post) {
        return new PostResponseDto(post);
    }
}
