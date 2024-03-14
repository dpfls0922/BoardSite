package com.Board.Board.Service;

import com.Board.Board.Domain.Entity.Member;
import com.Board.Board.Domain.Entity.Board;
import com.Board.Board.Domain.Repository.BoardRepository;
import com.Board.Board.Domain.Repository.MemberRepository;
import com.Board.Board.Dto.BoardDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Integer savePost(BoardDto boardDto, String userid){
        Member member = memberRepository.findByUserid(userid)
                .orElseThrow(() -> new UsernameNotFoundException("아이디가 존재하지 않습니다"));

        Board result = Board.builder()
                .subject(boardDto.getSubject())
                .content(boardDto.getContent())
                .name(boardDto.getName())
                .hitcount(0)
                .member(member)
                .build();
        boardRepository.save(result);

        return result.getNum();
    }
    @Transactional
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }
    @Transactional
    public List<Board> getAllBoardsReversed() {
        List<Board> boards = boardRepository.findAll();
        Collections.reverse(boards);
        return boards;
    }
    @Transactional
    public BoardDto  getBoard(int num) {
        Optional<Board> optionalBoard = boardRepository.findById(num);
        if (optionalBoard.isEmpty()) {
            throw new EntityNotFoundException("존재하지 않는 게시글입니다");
        }

        Board board = optionalBoard.get();
        BoardDto boardDto = BoardDto.builder()
                .num(board.getNum())
                .name(board.getName())
                .subject(board.getSubject())
                .content(board.getContent())
                .hitCount(board.getHitCount())
                .createdDate(board.getCreatedDate())
                .updatedDate(board.getUpdatedDate())
                .build();
        return boardDto;
    }
    @Transactional
    public void increaseHitCount(int num) {
        Board board = boardRepository.findById(num).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 게시글입니다"));
        board.setHitCount(board.getHitCount() + 1);
        boardRepository.save(board);
    }

    @Transactional
    public void deletePost(int num) {
        boardRepository.deleteById(num);
    }

    @Transactional
    public String findUserIdByPostId(int num) {
        Board board = boardRepository.findById(num).orElse(null);
        if (board != null) {
            return board.getName();
        } else {
            return null;
        }
    }
}
