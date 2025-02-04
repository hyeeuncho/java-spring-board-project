package com.spring.board.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostDTO {
    private Long id;
    private String title;
    private String content;
    private Long userId;
}