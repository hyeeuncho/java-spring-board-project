package com.spring.board.DTO;

public class UserDTO {
    private Long user_id;
    private String name;
    private String nickname;

    public void UserDto(Long user_id, String name, String nickname) {
        this.user_id = user_id;
        this.name = name;
        this.nickname = nickname;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}