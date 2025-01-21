package com.spring.board.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostDTO {
    private Long post_id;
    private String title;
    private String content;
    private String username;
}