package com.spring.board.controller;


import com.spring.board.dto.PostDTO;
import com.spring.board.entity.Post;
import com.spring.board.entity.User;
import com.spring.board.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class PostController {
    @Autowired
    PostService postService;

    //게시글 조회
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        try {
            List<Post> posts = postService.getAllPosts();
            if (posts.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(posts);
            }
            return ResponseEntity.ok(posts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    // 게시글 작성 폼
    @GetMapping("/post/form")
    public String showPostForm(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null){
            model.addAttribute("userId", user.getId());
        }else {
            model.addAttribute("userId", null);
        }
        return "postForm";
    }

    //게시글 작성
    @PostMapping("/post")
    public ResponseEntity<String> createPost(@RequestBody PostDTO postDTO) {
        try {
            postService.savePost(postDTO);
            return ResponseEntity.ok("게시글 등록 완료");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시글 등록 중 오류 발생");
        }
    }
}
