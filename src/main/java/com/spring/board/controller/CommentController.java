package com.spring.board.controller;

import com.spring.board.dto.CommentDTO;
import com.spring.board.dto.PostDTO;
import com.spring.board.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    // 댓글 작성
    @PostMapping("/comment")
    public ResponseEntity<Map<String, Object>> createComment(@RequestBody CommentDTO commentDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            commentService.saveComment(commentDTO);
            response.put("success", true);
            response.put("message", "댓글 등록 완료");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "댓글 등록 중 오류 발생");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // 댓글 삭제
    @DeleteMapping("comment/{commentId}")
    public ResponseEntity<Map<String,Object>> deleteComment(@PathVariable Long commentId){
        Map<String, Object> response = new HashMap<>();
        try {
            commentService.deleteComment(commentId);
            response.put("success", true);
            response.put("message", "댓글 삭제 완료");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "댓글 삭제 중 오류 발생");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
