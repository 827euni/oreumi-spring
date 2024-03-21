package com.example.blog.service;

import com.example.blog.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailService implements UserDetailsService {
    private UserRepository userRepository;

    public UserDetailService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 주어진 이메일을 통해서 사용자 정보를 불러오고, 만약 사용자가 존재하지 않는다면 예외를 발생시킴.
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new IllegalArgumentException(email));
    }
}
