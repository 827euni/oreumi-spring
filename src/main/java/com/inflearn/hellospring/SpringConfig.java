package com.inflearn.hellospring;

import com.inflearn.hellospring.aop.TimeTraceAop;
import com.inflearn.hellospring.repository.MemberRepository;
import com.inflearn.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// 이거 원래는 controller을 제외한 service랑 repository는 제거 하고 작성해야함.

@Configuration
public class SpringConfig {
//    private final DataSource dataSource;
//    private final EntityManager em;
//    public SpringConfig(DataSource dataSource, EntityManager em) {
//        this.dataSource = dataSource;
//        this.em = em;
//    }


    private final MemberRepository memberRepository;
    @Autowired
    public SpringConfig(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    @Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }
//    @Bean
//    public MemberRepository memberRepository() {
//// return new MemoryMemberRepository();
//// return new JdbcMemberRepository(dataSource);
//// return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}