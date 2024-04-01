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
    public Long savePost(BoardDto boardDto, String userid){
        Member member = memberRepository.findByUserid(userid)
                .orElseThrow(() -> new UsernameNotFoundException("아이디가 존재하지 않습니다"));

        Board result = Board.builder()
                .subject(boardDto.getSubject())
                .content(boardDto.getContent())
                .writer(boardDto.getWriter())
                .hitcount(0)
                .member(member)
                .build();

        boardRepository.save(result);

        return result.getId();
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
    public BoardDto getBoard(Long id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (optionalBoard.isEmpty()) {
            throw new EntityNotFoundException("존재하지 않는 게시글입니다");
        }

        Board board = optionalBoard.get();

        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .writer(board.getWriter())
                .subject(board.getSubject())
                .content(board.getContent())
                .hitCount(board.getHitCount())
                .createdDate(board.getCreatedDate())
                .updatedDate(board.getUpdatedDate())
                .build();
        return boardDto;
    }

    @Transactional
    public Long updatePost(Long id, BoardDto boardDto){
        Board board = boardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 게시글입니다"));

        board.update(boardDto.getSubject(), boardDto.getContent());
        boardRepository.save(board);

        return board.getId();
    }
    @Transactional
    public void increaseHitCount(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 게시글입니다"));
        board.setHitcount(board.getHitCount() + 1);
        boardRepository.save(board);
    }

    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public String findUserIdByPostId(Long id) {
        Board board = boardRepository.findById(id).orElse(null);
        if (board != null) {
            return board.getWriter();
        } else {
            return null;
        }
    }
}
