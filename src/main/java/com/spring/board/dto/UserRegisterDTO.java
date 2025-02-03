package com.spring.board.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRegisterDTO {
    private String name;
    private String username;
    private String email;
    private String password;
}
