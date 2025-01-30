package com.spring.board.repository;

import com.spring.board.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    // 이메일로 사용자 찾기
    User findByEmail(String email);
}
