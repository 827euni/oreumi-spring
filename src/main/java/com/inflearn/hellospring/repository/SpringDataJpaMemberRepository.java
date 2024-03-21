package com.inflearn.hellospring.repository;

import com.inflearn.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// LONG : PK(ID)
public interface SpringDataJpaMemberRepository extends JpaRepository<Member,
        Long>, MemberRepository {
    @Override
    Optional<Member> findByName(String name);
}