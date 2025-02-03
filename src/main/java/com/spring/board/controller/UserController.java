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

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<Map<String, Object>> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            userService.register(userRegisterDTO);
            response.put("success", true);
            response.put("message", "회원가입 성공");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "회원가입 중 오류 발생");
            return ResponseEntity.status(500).body(response);
        }
    }



    // 로그인 페이지
    @GetMapping("/users/login")
    public String showLoginPage(){
        return "login";
    }
    // 로그인 API
    @PostMapping("/users/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserRegisterDTO userRegisterDTO, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        User user = userService.getUserByUsername(userRegisterDTO.getUsername());

        if (user != null && userService.checkPassword(user, userRegisterDTO.getPassword())) {
            session.setAttribute("user", user);

            response.put("success", true);
            response.put("message", "로그인 성공");

            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "잘못된 비밀번호");
            return ResponseEntity.status(401).body(response);
        }
    }

}