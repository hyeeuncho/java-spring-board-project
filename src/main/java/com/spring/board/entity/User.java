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
    @Column(name = "user_id")  // DB 컬럼명은 여전히 user_id로 사용할 수 있습니다.
    private Long id;

    private String name;
    private String nickname;
    private String email;

    @Column(nullable = false)
    private String password;

    @Builder
    public User(Long id, String name, String nickname, String email, String password) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }
}
