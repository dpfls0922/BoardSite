package com.Board.Board.Service;

import com.Board.Board.Domain.Entity.Board;
import com.Board.Board.Domain.Entity.Member;
import com.Board.Board.Domain.Repository.BoardRepository;
import com.Board.Board.Domain.Repository.MemberRepository;
import com.Board.Board.Dto.MemberDto;
import com.Board.Board.Dto.MemberSignupDto;
import com.Board.Board.Dto.MemberUpdateDto;
import org.springframework.ui.Model;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    /* 회원가입 시, 유효성 및 중복 검사 */
    @Transactional(readOnly = true)
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        /* 유효성 및 중복 검사에 실패한 필드 목록을 받음 */
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }

        return validatorResult;
    }

    public void messageHandling(Errors errors, Model model) {
        Map<String, String> validatorResult = validateHandling(errors);

        /* 유효성 검사를 통과하지 못한 필드와 메세지 핸들링 */
        for (String key : validatorResult.keySet()) {
            model.addAttribute(key, validatorResult.get(key));
        }
    }
    @Transactional
    public Long join(MemberSignupDto memberSignupDto) {

        memberSignupDto.setPassword1(passwordEncoder.encode(memberSignupDto.getPassword1()));
        Member member = Member.builder()
                .email(memberSignupDto.getEmail())
                .username(memberSignupDto.getUsername())
                .userid(memberSignupDto.getUserid())
                .password(memberSignupDto.getPassword1())
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

        return memberRepository.save(member).getId();
    }

    public boolean withdrawal(String userid, String password) {
        Member member = memberRepository.findByUserid(userid)
                .orElseThrow(() -> new UsernameNotFoundException("아이디가 존재하지 않습니다"));
        if (passwordEncoder.matches(password, member.getPassword())) {
            List<Board> boards = boardRepository.findByMember(member);
            for (Board board : boards) {
                board.setName("(탈퇴한 회원)");
                board.setMember(null);
            }
            memberRepository.delete(member);
            return true;
        } else {
            return false;
        }
    }
}
