package com.Board.Board.Validator;

import com.Board.Board.Domain.Repository.MemberRepository;
import com.Board.Board.Dto.MemberDto;

import com.Board.Board.Dto.MemberSignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.springframework.validation.Errors;

@Component
@RequiredArgsConstructor
public class CheckSignupValidator extends AbstractValidator<MemberSignupDto>{
    private final MemberRepository memberRepository;
    @Override
    protected void doValidate(MemberSignupDto dto, Errors errors) {
        if(memberRepository.existsByUserid(dto.getUserid())) {
            errors.rejectValue("userid", "아이디 중복 오류", "이미 사용 중인 아이디입니다");
        }
        if(memberRepository.existsByEmail(dto.getEmail())) {
            errors.rejectValue("email", "이메일 중복 오류", "이미 사용 중인 이메일입니다");
        }
        if(!isPasswordMatching(dto.getPassword1(), dto.getPassword2())) {
            errors.rejectValue("password2", "비밀번호 일치 오류", "패스워드가 일치하지 않습니다");
        }
    }
    public boolean isPasswordMatching(String password1, String password2) {
        if (password1.equals(password2)){
            return true;
        }else{
            return false;
        }
    }
}
