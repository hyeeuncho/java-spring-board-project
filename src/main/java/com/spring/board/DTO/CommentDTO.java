package com.spring.board.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentDTO {
    private Long comment_id;
    private String content;
    private String username;
    private Long postId;
}