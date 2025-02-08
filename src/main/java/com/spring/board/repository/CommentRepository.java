package com.spring.board.repository;

import com.spring.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    // 특정 게시글의 댓글 검색
    List<Comment> findByPostId(Long postId);



}
