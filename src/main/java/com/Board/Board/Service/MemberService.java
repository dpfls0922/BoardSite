package com.Board.Board.Service;

import com.Board.Board.Domain.Entity.Member;
import com.Board.Board.Domain.Repository.MemberRepository;
import com.Board.Board.Dto.MemberDto;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

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

    @Transactional
    public MemberDto findMember(String userid) {
        Optional<Member> memberOptional = memberRepository.findByUserid(userid);
        Member member = memberOptional.orElseThrow(() -> new UsernameNotFoundException("아이디가 존재하지 않습니다"));

        MemberDto memberDto = MemberDto.builder()
                .member(member)
                .build();

        return memberDto;
    }

    public Long updateMember(MemberDto memberUpdatedDto) {
        Member member = memberRepository.findByUserid(memberUpdatedDto.getUserid())
                .orElseThrow(()->new UsernameNotFoundException("아이디가 존재하지 않습니다"));


        memberUpdatedDto.setPassword(passwordEncoder.encode(memberUpdatedDto.getPassword()));

        member.updateUsername(memberUpdatedDto.getUsername());
        member.updateUserEmail(memberUpdatedDto.getEmail());
        member.updatePassword(memberUpdatedDto.getPassword());

        memberRepository.save(member);

        return member.getId();
    }

    public boolean withdrawal(String userid, String password) {
        Member member = memberRepository.findByUserid(userid)
                .orElseThrow(() -> new UsernameNotFoundException("아이디가 존재하지 않습니다"));

        if (passwordEncoder.matches(password, member.getPassword())) {
            memberRepository.delete(member);
            return true;
        } else {
            return false;
        }
    }
}
