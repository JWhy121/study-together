package com.elice.studytogether.repository;

import com.elice.studytogether.domain.Comment;
import com.elice.studytogether.dto.CommentResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {


    List<Comment> findByPostId(Long id);
}