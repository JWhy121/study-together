package com.elice.studytogether.repository;

import com.elice.studytogether.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}

