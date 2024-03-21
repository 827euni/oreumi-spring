package com.example.blog.controller;

import com.example.blog.dto.AddUserRequest;
import com.example.blog.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;

public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/user")
    public String signup(AddUserRequest request){
        userService.save(request); // 회원가입(저장)
        return "redirect:/login"; // 회원가입 처리 후 로그인 페이지로 이동
    }
}
