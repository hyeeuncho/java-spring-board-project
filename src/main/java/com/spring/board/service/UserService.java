package com.spring.board.service;

import com.spring.board.entity.User;
import com.spring.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //사용자 id로 사용자 찾기
    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
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