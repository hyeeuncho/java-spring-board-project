package com.spring.board.controller;

import com.spring.board.dto.UserRegisterDTO;
import com.spring.board.entity.User;
import com.spring.board.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    // 메인 페이지
    @GetMapping("/index")
    public String index(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        if (user != null){
            model.addAttribute("username", user.getUsername());
        }else {
            model.addAttribute("username", null);
        }
        return "index";
    }


    // 회원가입 페이지
    @GetMapping("/users/register")
    public String showRegisterPage() {
        return "register";
    }
    // 회원가입 API
    @PostMapping("/users/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        // 사용자 등록
        userService.register(userRegisterDTO);
        return ResponseEntity.ok("회원가입 성공");
    }


    // 로그인 페이지
    @GetMapping("/users/login")
    public String showLoginPage(){
        return "login";
    }
    // 로그인 API
    @PostMapping("/users/login")
    public ResponseEntity<String> login(@RequestBody UserRegisterDTO userRegisterDTO, HttpSession session) {
        User user = userService.getUserByUsername(userRegisterDTO.getUsername());
        if (user != null && userService.checkPassword(user, userRegisterDTO.getPassword())) {
            session.setAttribute("user",user); //세션에 저장.
            return ResponseEntity.ok("로그인 성공");
        } else {
            return ResponseEntity.status(401).body("잘못된 비밀번호");
        }
    }
}