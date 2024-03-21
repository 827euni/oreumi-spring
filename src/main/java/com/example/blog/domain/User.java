package com.example.blog.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Builder
    public User(String email, String password, String auth) {
        this.email = email;
        this.password = password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    // 계정 만료 여부 반환 (true: 만료 안됨)
    @Override
    public boolean isAccountNonExpired() {
        //사용자의 계정이 특정 날짜를 기준으로 만료된다면, 현 날짜와 비교해서 계정의 만료 여부 판단
        return true;
    }

    // 계정 잠금 여부 반환 (true: 잠금 안됨)
    @Override
    public boolean isAccountNonLocked() {
        //사용자가 로그인을 여러번 실패해서 계정이 잠겨있다면 false를 반환하여 로그인을 거부할 수 있음.
        return true;
    }

    // 패스워드의 만료 여부 반환 (true: 만료 안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        //사용자의 비밀번호를 일정 기간 지나서 변경햐야하는 정책이 있을 때,
        //비밀번호가 만료되었다면 사용자의 로그인을 거부할 수 있음.
        return true;
    }

    // 계정 사용 여부 반환 (true: 사용 가능)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
