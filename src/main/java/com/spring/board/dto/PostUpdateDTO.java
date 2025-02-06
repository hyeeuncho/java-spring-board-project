package com.spring.board.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostUpdateDTO {
    private String title;
    private String content;
}
