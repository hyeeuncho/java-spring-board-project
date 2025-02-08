package com.spring.board.service;

import com.spring.board.dto.CommentDTO;
import com.spring.board.entity.Comment;
import com.spring.board.entity.Post;
import com.spring.board.entity.User;
import com.spring.board.repository.CommentRepository;
import com.spring.board.repository.PostRepository;
import com.spring.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;

    // 특정 게시글에 대한 모든 댓글 찾기
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    // 댓글 저장
    public void saveComment(CommentDTO commentDTO) {
        User user = userRepository.findById(commentDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        Post post = postRepository.findById(commentDTO.getPostId())
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        Comment comment = Comment.builder()
                .content(commentDTO.getContent())
                .user(user)
                .post(post)
                .build();
        commentRepository.save(comment);
    }

    // 댓글 삭제
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}