package com.example.pinterest.Service;

import com.example.pinterest.Domain.Entity.Member;
import com.example.pinterest.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long save(Member member) {
        String encodePw = passwordEncoder.encode(member.getPassword());
        member.encodingPw(member,encodePw); // 패스워드 인코딩
        member.giveAuth(member);

        return memberRepository.save(member).getId();
    }
}
