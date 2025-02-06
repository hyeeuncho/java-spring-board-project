package com.spring.board.service;

import com.spring.board.dto.PostDTO;
import com.spring.board.dto.PostUpdateDTO;
import com.spring.board.entity.User;
import com.spring.board.repository.PostRepository;
import com.spring.board.entity.Post;

import com.spring.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

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
    public void savePost(PostDTO postDTO) {
        User user = userRepository.findById(postDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        Post post = Post.builder()
                .title(postDTO.getTitle())
                .content(postDTO.getContent())
                .user(user)
                .build();
        postRepository.save(post);
    }

    // 게시글 수정
    public void updatePost(Long postId, PostUpdateDTO postUpdateDTO){
        Post post = postRepository.findById(postId).orElse(null);
        if (post != null){
            if (postUpdateDTO.getTitle() != null){
                post.setTitle(postUpdateDTO.getTitle());
            }
            if (postUpdateDTO.getContent() != null){
                post.setContent(postUpdateDTO.getContent());
            }
            postRepository.save(post);
        }
    }

    // 게시글 삭제
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}