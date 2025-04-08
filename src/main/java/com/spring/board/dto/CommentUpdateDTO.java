package com.spring.board.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentUpdateDTO {
    private String content;
    private Long commentId;
    private Long postId;
}
