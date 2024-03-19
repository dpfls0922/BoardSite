package com.Board.Board.Domain.Repository;

import com.Board.Board.Domain.Entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    /*
    * 회원가입 시 유효성 체크
    */
    boolean existsByUserid(String userid);
    boolean existsByEmail(String email);
    Optional<Member> findByUserid(String userid);
}
