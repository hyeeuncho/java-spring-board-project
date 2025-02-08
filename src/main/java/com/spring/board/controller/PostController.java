package com.spring.board.controller;


import com.spring.board.dto.PostDTO;
import com.spring.board.dto.PostUpdateDTO;
import com.spring.board.entity.Comment;
import com.spring.board.entity.Post;
import com.spring.board.entity.User;
import com.spring.board.service.CommentService;
import com.spring.board.service.PostService;
import com.spring.board.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;

    // 게시글 조회
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

    // 게시글 상세
    @GetMapping("/post/{postId}")
    public String getPost(@PathVariable Long postId, Model model,HttpSession session) {
        try {
            Post post = postService.getPostById(postId).orElse(null);
            User user = (User) session.getAttribute("user");
            List<Comment> comment = commentService.getCommentsByPostId(postId);
            if (post != null) {
                String username = post.getUser().getUsername();

                if (user != null) {
                    model.addAttribute("user", user.getId());
                } else {
                    model.addAttribute("user", null);
                }

                model.addAttribute("post", post);
                model.addAttribute("username", username);
                model.addAttribute("comments",comment);
                return "post";
            } else {
                model.addAttribute("error", "게시글을 찾을 수 없습니다.");
                return "post";
            }
        } catch (Exception e) {
            model.addAttribute("error", "게시글 조회 중 오류 발생");
            return "post";
        }
    }

    // 게시글 작성 폼
    @GetMapping("/post")
    public String showPostForm(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null){
            model.addAttribute("userId", user.getId());
        }else {
            model.addAttribute("userId", null);
        }
        return "postForm";
    }

    // 게시글 작성
    @PostMapping("/post")
    public ResponseEntity<Map<String, Object>> createPost(@RequestBody PostDTO postDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            postService.savePost(postDTO);
            response.put("success", true);
            response.put("message", "게시글 등록 완료");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "게시글 등록 중 오류 발생");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // 게시글 삭제
    @DeleteMapping("post/{postId}")
    public ResponseEntity<Map<String,Object>> deletePost(@PathVariable Long postId){
        Map<String, Object> response = new HashMap<>();
        try {
            postService.deletePost(postId);
            response.put("success", true);
            response.put("message", "게시글 삭제 완료");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "게시글 삭제 중 오류 발생");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // 게시글 수정 폼
    @GetMapping("post/form/{postId}")
    String showUpdateForm(@PathVariable Long postId, Model model){
        Post post = postService.getPostById(postId).orElse(null);
        model.addAttribute("post", post);
        return "postUpdateForm";
    }

    // 게시글 수정 - 전체
    @PutMapping("post/{postId}")
    public ResponseEntity<Map<String,Object>> updatePostAll(@PathVariable Long postId, @RequestBody PostUpdateDTO postUpdateDTO){
        Map<String, Object> response = new HashMap<>();
        try {
            postService.updatePost(postId, postUpdateDTO);
            response.put("success", true);
            response.put("message", "게시글 수정 완료");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "게시글 수정 중 오류 발생");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    // 게시글 수정 - 부분
    @PatchMapping("post/{postId}")
    public ResponseEntity<Map<String,Object>> updatePost(){
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("success", true);
            response.put("message", "게시글 수정 완료");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "게시글 수정 중 오류 발생");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


}
