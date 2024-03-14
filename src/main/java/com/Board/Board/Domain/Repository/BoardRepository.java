package com.Board.Board.Domain.Repository;

import com.Board.Board.Domain.Entity.Board;
import com.Board.Board.Domain.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface BoardRepository extends JpaRepository<Board, Integer> {
    List<Board> findByMember(Member member);
}
