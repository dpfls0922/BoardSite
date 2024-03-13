package com.Board.Board.Service;

import com.Board.Board.Domain.Entity.Board;
import com.Board.Board.Domain.Repository.BoardRepository;
import com.Board.Board.Dto.BoardDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Integer savePost(BoardDto boardDto){
        return boardRepository.save(boardDto.toEntity()).getNum();
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
        Board board = boardRepository.findById(num).orElseThrow(() -> new EntityNotFoundException("Board not found with num: " + num));
        board.setHitcount(board.getHitCount() + 1);
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
