package com.spring.board.service;

import com.spring.board.repository.PostRepository;
import com.spring.board.entity.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    // 게시글 ID로 게시글 찾기
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    // 모든 게시글 찾기
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // 특정 사용자가 작성한 게시글 찾기
    public List<Post> getPostsByAuthorId(Long userId) {
        return postRepository.findByUserId(userId);
    }

    // 게시글 저장
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    // 게시글 삭제
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}