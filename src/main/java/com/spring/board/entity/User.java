package com.spring.board.entity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private String name;
    private String nickname;
    private String password;
    private String email;

    @Builder
    public User(Long user_id, String name, String nickname, String email, String password) {
        this.user_id = user_id;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }
}
