package com.inflearn.hellospring.repository;

import com.inflearn.hellospring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    private final EntityManager em; //JPA를 쓰려면 이렇게 의존성을 주입 받아야함.


    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // 영원히 저장하다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member =  em.find(Member.class, id);
        return Optional.ofNullable(member); // null로 반환될 수도 잇으니까.
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result =  em.createQuery("select m from Member m where m.name = : name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny(); // 하나만 찾으려고 했으니까! PK기반이 아니므로
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();

        // return em.createQuery("select m from Member m", Member.class)
        //                .getResultList();

        /*
        select m from Member(엔티티) (as/생략) m", Member.class
        m -> 멤버 엔티티 자체를 셀렉트함.
         */
    }
}
