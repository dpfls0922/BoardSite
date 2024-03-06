package com.Board.Board.Service;

import com.Board.Board.Domain.Entity.Board;
import com.Board.Board.Domain.Repository.BoardRepository;
import com.Board.Board.Dto.BoardDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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
        Board board = boardRepository.findById(num).get();

        BoardDto boardDto = BoardDto.builder()
                .num(board.getNum())
                .name(board.getName())
                .subject(board.getSubject())
                .content(board.getContent())
                .createdDate(board.getCreatedDate())
                .updatedDate(board.getUpdatedDate())
                .build();
        return boardDto;
    }

    @Transactional
    public void deletePost(int num) {
        boardRepository.deleteById(num);
    }
}
