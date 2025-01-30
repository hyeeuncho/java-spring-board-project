package com.spring.board.service;

import com.spring.board.dto.UserSignUpDTO;
import com.spring.board.entity.User;
import com.spring.board.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();  // BCryptPasswordEncoder 객체 생성
    }

    // 회원가입
    public User register(UserSignUpDTO userSignUpDTO) {

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(userSignUpDTO.getPassword());

        // User 엔티티 생성 및 저장 (빌더 패턴 사용)
        User user = User.builder()
                .name(userSignUpDTO.getName())
                .nickname(userSignUpDTO.getNickname())
                .email(userSignUpDTO.getEmail())
                .password(encodedPassword)  // 암호화된 비밀번호 저장
                .build();

        return userRepository.save(user);
    }

    // 로그인 서비스 시 비밀번호 비교
    public boolean checkPassword(User user, String rawPassword) {
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

    //사용자 id로 사용자 찾기
    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    //사용자 email로 사용자 찾기
    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    // 사용자 저장
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // 사용자 삭제
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}