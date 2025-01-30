package com.spring.board.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSignUpDTO {
    private String name;
    private String nickname;
    private String email;
    private String password;
}
