package com.Board.Board.Service;

import com.Board.Board.Domain.Entity.Member;
import com.Board.Board.Domain.Repository.MemberRepository;
import com.Board.Board.Dto.MemberDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class MemberService {

    private PasswordEncoder passwordEncoder;
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder){
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Long join(MemberDto memberDto) {

        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        Member member = Member.builder()
                .email(memberDto.getEmail())
                .username(memberDto.getUsername())
                .userid(memberDto.getUserid())
                .password(memberDto.getPassword())
                .build();
        return memberRepository.save(member).getId();
    }
}
