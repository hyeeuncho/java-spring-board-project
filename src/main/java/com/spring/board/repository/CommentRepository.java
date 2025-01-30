package com.spring.board.repository;

import com.spring.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    // 특정 게시글의 댓글 검색
    List<Comment> findByPostId(Long postId);

    // 작성자가 특정 사용자 ID인 댓글 검색
    List<Comment> findByUserId(Long userId);


    // 특정 날짜 이후에 작성된 댓글 검색
    List<Comment> findByCreatedAtAfter(LocalDateTime date);
}
