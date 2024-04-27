package com.elice.studytogether.repository;

import com.elice.studytogether.domain.Post;
import com.elice.studytogether.dto.PostResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByBoardId(Long boardId);

    Post findPostById(Long id);

    List<Post> findByTitleContaining(String keyword);
}