package com.spring.board.service;

import com.spring.board.entity.Comment;
import com.spring.board.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    // 댓글 ID로 댓글 찾기
    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    // 특정 게시글에 대한 모든 댓글 찾기
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    // 특정 키워드를 포함하는 댓글들을 조회하는 메서드
    public List<Comment> getCommentsByKeyword(String keyword) {
        return commentRepository.findByKeyword(keyword);  // findByContentContaining 메서드 호출
    }

    // 댓글 저장
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    // 댓글 삭제
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}