package com.Board.Board.Dto;

import com.Board.Board.Domain.Entity.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private String email;
    private String username;
    private String userid;
    private String password;

    @Builder
    public MemberDto(Member member) {
        this.email = member.getEmail();
        this.username = member.getUsername();
        this.userid = member.getUserid();
    }
}
