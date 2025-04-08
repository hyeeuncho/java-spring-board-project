package com.spring.board.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentDTO {
    private String content;
    private Long userId;
    private Long postId;
}