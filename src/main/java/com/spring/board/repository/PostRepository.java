package com.spring.board.repository;

import com.spring.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    // 제목으로 게시글 검색
    List<Post> findByTitle(String title);

    // 특정 사용자가 작성한 게시글 검색
    List<Post> findByUserId(Long userId);

    // 특정 날짜 이후에 작성된 게시글 검색
    List<Post> findByCreatedAtAfter(LocalDateTime date);
}
