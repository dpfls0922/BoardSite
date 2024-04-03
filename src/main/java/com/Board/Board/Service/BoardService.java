package com.Board.Board.Service;

import com.Board.Board.Domain.Entity.Category;
import com.Board.Board.Domain.Entity.Member;
import com.Board.Board.Domain.Entity.Board;
import com.Board.Board.Domain.Entity.BoardCategory;
import com.Board.Board.Domain.Repository.BoardCategoryRepository;
import com.Board.Board.Domain.Repository.BoardRepository;
import com.Board.Board.Domain.Repository.CategoryRepository;
import com.Board.Board.Domain.Repository.MemberRepository;
import com.Board.Board.Dto.BoardDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final BoardCategoryRepository boardCategoryRepository;

    @Transactional
    public Long savePost(BoardDto boardDto, String userid, List<String> categoryNames){
        Member member = memberRepository.findByUserid(userid)
                .orElseThrow(() -> new UsernameNotFoundException("아이디가 존재하지 않습니다"));

        Board board = Board.builder()
                .subject(boardDto.getSubject())
                .content(boardDto.getContent())
                .writer(boardDto.getWriter())
                .hitcount(0)
                .member(member)
                .build();

        List<BoardCategory> boardCategories = valiateAndAddCategoriesToList(categoryNames, board);
        board.setBoardCategories(boardCategories);
        boardRepository.save(board);

        for (BoardCategory boardCategory : boardCategories) {
            boardCategoryRepository.save(boardCategory);
        }
        return board.getId();
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
        Board board = boardRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("존재하지 않는 게시글입니다"));

        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .writer(board.getWriter())
                .subject(board.getSubject())
                .content(board.getContent())
                .hitCount(board.getHitCount())
                .createdDate(board.getCreatedDate())
                .updatedDate(board.getUpdatedDate())
                .build();
        boardDto.setSelectedCategories(board.getBoardCategories().stream()
                .map(BoardCategory::getCategory).map(Category::getType).collect(Collectors.toList()));

        return boardDto;
    }

    @Transactional
    public Long updatePost(Long id, BoardDto boardDto, List<String> categoryNames){
        Board board = boardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 게시글입니다"));
        board.update(boardDto.getSubject(), boardDto.getContent());

        board.getBoardCategories().clear();
        List<BoardCategory> boardCategories = valiateAndAddCategoriesToList(categoryNames, board);
        board.getBoardCategories().addAll(boardCategories);
        boardRepository.save(board);

        boardCategoryRepository.deleteByBoard(board);
        for (BoardCategory boardCategory : boardCategories) {
            boardCategoryRepository.save(boardCategory);
        }
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
        Board board = boardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 게시글입니다"));
        boardCategoryRepository.deleteByBoard(board);
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

    private List<BoardCategory> valiateAndAddCategoriesToList(List<String> categoryNames, Board board) {
        List<BoardCategory> boardCategories = new ArrayList<>();

        for (String categoryName : categoryNames) {
            Category category = categoryRepository.findByType(categoryName)
                    .orElseThrow(() -> new EntityNotFoundException("카테고리를 찾을 수 없습니다: " + categoryName));
            boardCategories.add(new BoardCategory(board, category));
        }
        return boardCategories;
    }
}
