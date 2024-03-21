package com.example.blog.service;

import com.example.blog.domain.User;
import com.example.blog.dto.AddUserRequest;
import com.example.blog.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder encoder; // 암호화

    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder){
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public User save(AddUserRequest dto){
        return userRepository.save(
                User.builder()
                        .email(dto.getEmail())
                        .password(encoder.encode(dto.getPassword()))
                        .build()
        );
    }
}
