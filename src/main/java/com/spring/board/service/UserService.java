package com.spring.board.service;

import com.spring.board.dto.UserRegisterDTO;
import com.spring.board.entity.User;
import com.spring.board.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();  // BCryptPasswordEncoder 객체 생성
    }

    // 회원가입
    public User register(UserRegisterDTO userRegisterDTO) {
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(userRegisterDTO.getPassword());

        // User 엔티티 생성 및 저장 (빌더 패턴 사용)
        User user = User.builder()
                .name(userRegisterDTO.getName())
                .username(userRegisterDTO.getUsername())
                .email(userRegisterDTO.getEmail())
                .password(encodedPassword)  // 암호화된 비밀번호 저장
                .build();

        return userRepository.save(user);
    }

    // 로그인 서비스 시 비밀번호 비교
    public boolean checkPassword(User user, String rawPassword) {
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

    //사용자id로 사용자 찾기
    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    
}