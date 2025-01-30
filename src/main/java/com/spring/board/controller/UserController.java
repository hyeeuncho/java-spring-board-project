package com.spring.board.controller;

import com.spring.board.dto.UserSignUpDTO;
import com.spring.board.entity.User;
import com.spring.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users/register")
    public String showRegistrationForm() {
        return "register";
    }

    @GetMapping("/index")
    public String goMain(){
        return "index";
    }

    // 회원가입 API
    @PostMapping("/users/signup")
    public ResponseEntity<String> signUp(@RequestBody UserSignUpDTO userSignUpDTO) {
        // 사용자 등록
        userService.register(userSignUpDTO);
        return ResponseEntity.ok("회원가입 성공");
    }

    // 로그인 시 비밀번호 확인 API
    @PostMapping("/users/login")
    public ResponseEntity<String> login(@RequestBody UserSignUpDTO userSignUpDTO) {
        User user = userService.getUserByEmail(userSignUpDTO.getEmail());  // 이메일로 사용자 찾기
        if (user != null && userService.checkPassword(user, userSignUpDTO.getPassword())) {
            return ResponseEntity.ok("로그인 성공");
        } else {
            return ResponseEntity.status(401).body("잘못된 비밀번호");
        }
    }
}