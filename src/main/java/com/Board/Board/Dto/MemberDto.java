package com.Board.Board.Dto;

import com.Board.Board.Domain.Entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    @NotBlank(message="이메일을 입력해주세요")
    @Email(message = "올바른 이메일 주소를 입력해주세요")
    private String email;

    @NotBlank(message="이름을 입력해주세요")
    @Size(min = 2, max = 15, message = "2 ~ 15자 사이로 입력해주세요")
    private String username;

    @NotBlank(message="아이디를 입력해주세요")
    @Size(min = 5, max = 15, message = "5 ~ 15자 사이로 입력해주세요")
    private String userid;

    @NotBlank(message="비밀번호를 입력해주세요")
    private String password;

    @Builder
    public MemberDto(Member member) {
        this.email = member.getEmail();
        this.username = member.getUsername();
        this.userid = member.getUserid();
    }
}
