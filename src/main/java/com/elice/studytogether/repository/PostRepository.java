package com.elice.studytogether.repository;

import com.elice.studytogether.domain.Post;
import com.elice.studytogether.dto.PostResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByBoardId(Long boardId, Pageable pageable);

    Page<Post> findByBoardIdOrderByCompletedAsc(Long boardId, Pageable pageable);


    Page<Post> findByTitleContaining(String keyword, Pageable pageable);
}