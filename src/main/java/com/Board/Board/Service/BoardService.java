package com.Board.Board.Service;

import com.Board.Board.Domain.Entity.Board;
import com.Board.Board.Domain.Repository.BoardRepository;
import com.Board.Board.Dto.BoardDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class BoardService {
    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Integer savePost(BoardDto boardDto){
        return boardRepository.save(boardDto.toEntity()).getNum();
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }
    public List<Board> getAllBoardsReversed() {
        List<Board> boards = boardRepository.findAll();
        Collections.reverse(boards);
        return boards;
    }
}
