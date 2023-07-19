package com.example.pinterest.Controller;

import com.example.pinterest.Domain.Entity.Member;
import com.example.pinterest.Service.MemberServiceImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Getter
@RequiredArgsConstructor
@Controller
@Slf4j
public class MemberController {

    private final MemberServiceImpl memberService;

    @GetMapping("/member/signIn")
    public String signIn() {return "members/signIn";}

    @GetMapping("/member/signUp")
    public String signUp(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "members/signUp";
    }

    @PostMapping("/member/signUp")
    public String signUp(@Valid MemberForm form){
        Member newMember = new Member(form.getUsername(), form.getPassword());
        newMember.giveAuth(newMember);
        memberService.save(newMember);
        return "redirect:/";
    }

}
