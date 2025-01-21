package com.spring.board.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDTO {
    private Long user_id;
    private String name;
    private String nickname;
}