package com.inflearn.hellospring.controller;

import com.inflearn.hellospring.domain.Member;
import com.inflearn.hellospring.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new") // 조회할 때 주로 사용
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") //폼 같은 곳에 넣어서 전달.
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; //홈 화면으로 돌아가기
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); // 모델에 데이터를 담을 때 사용하는 메소드
        return "members/memberList";

    }
}
