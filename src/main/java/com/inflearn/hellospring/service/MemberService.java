package com.inflearn.hellospring.service;

import com.inflearn.hellospring.domain.Member;
import com.inflearn.hellospring.repository.MemberRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 테스트 케이스의 레포지터리와 다른 레포지토리가 생성이 되었기 떄문에
    // 같은 인스턴스를 사용하게 하고 싶다면

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public Long join(Member member){
        //중복 회원은 X

//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> { //옵셔널이기 때문에 이렇게 꺼낼 수 있음.
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//                });
        validateDuplicateMember(member);


        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m-> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
