package com.Board.Board.Service;

import com.Board.Board.Domain.Entity.Member;
import com.Board.Board.Domain.Entity.MemberRole;
import com.Board.Board.Domain.Repository.MemberRepository;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberSecurityService implements UserDetailsService {
    private MemberRepository memberRepository;
    public MemberSecurityService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {

        System.out.print("userid : ");
        System.out.println(userid);
        Optional<Member> member = memberRepository.findByUserid(userid);
        if (member.isEmpty()) {
            System.out.println("가입된 유저가 없습니다");
            throw new UsernameNotFoundException("가입된 유저가 없습니다");
        }
        return toUserDetails(member.get());
    }

    private UserDetails toUserDetails(Member member) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        if ("admin".equals(member.getUserid())) {
            authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(MemberRole.USER.getValue()));
        }

        return User.builder()
                .username(member.getUserid())
                .password(member.getPassword())
                .authorities(authorities)
                .build();
    }
}
