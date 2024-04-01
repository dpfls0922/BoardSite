package com.Board.Board.Service;

import com.Board.Board.Domain.Entity.Member;
import com.Board.Board.Domain.Repository.BoardRepository;
import com.Board.Board.Domain.Repository.MemberRepository;
import com.Board.Board.Dto.BoardDto;
import com.Board.Board.Domain.Entity.Board;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Transactional
@DisplayName("BoardService 생성 테스트")
@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @Mock
    BoardRepository boardRepository;
    @Mock
    MemberRepository memberRepository;
    @InjectMocks
    BoardService boardService;

    @Test
    @DisplayName("글 생성하기")
    void savePostTest() {
        // given
        BoardDto boardDto = new BoardDto();
        boardDto.setId(1L);
        boardDto.setName("yerin");
        boardDto.setSubject("title");
        boardDto.setContent("content");

        String userid = "yerin";
        Member member =  Member.builder()
                .userid(userid)
                .build();

        Board boardEntity = Board.builder()
                .id(boardDto.getId())
                .name(boardDto.getName())
                .subject(boardDto.getSubject())
                .content(boardDto.getContent())
                .member(member)
                .build();

        when(memberRepository.findByUserid(anyString())).thenReturn(Optional.of(member));
        when(boardRepository.save(any())).thenAnswer(invocation -> {
            // 게시물이 저장되면 게시물 번호를 반환
            Board savedBoard = invocation.getArgument(0);
            savedBoard.setId(1L);
            return savedBoard;
        });

        // When
        Long savedBoardId = boardService.savePost(boardDto, userid);

        // Then
        verify(memberRepository, times(1)).findByUserid(userid);
        verify(boardRepository, times(1)).save(any(Board.class));
        assertEquals(boardEntity.getId(), savedBoardId);
    }

    @Test
    @DisplayName("모든 게시글 불러오기")
    void getAllBoards() {
        Board board1 = new Board();
        Board board2 = new Board();

        when(boardRepository.findAll()).thenReturn(Arrays.asList(board1, board2));

        List<Board> boards = boardService.getAllBoards();

        assertNotNull(boards, "게시물 목록은 null이 아니어야 합니다.");
        assertEquals(2, boards.size());
    }

    @Test
    @DisplayName("모든 게시글 역순으로 불러오기")
    void getAllBoardsReversed() {
        Board board1 = new Board();
        Board board2 = new Board();;
        board1.setId(1L);
        board2.setId(2L);

        when(boardRepository.findAll()).thenReturn(Arrays.asList(board1, board2));

        List<Board> reversedBoards = boardService.getAllBoardsReversed();

        assertNotNull(reversedBoards, "게시물 목록은 null이 아니어야 합니다.");
        assertEquals(2, reversedBoards.size());
        assertEquals(board2, reversedBoards.get(0), "첫 번째 게시물이 일치하지 않습니다.");
        assertEquals(board1, reversedBoards.get(1), "두 번째 게시물이 일치하지 않습니다.");
    }

    @Test
    @DisplayName("한 개의 게시글 불러오기")
    void getBoard() {
        Long boardId = 1L;
        Board board = new Board();
        board.setId(boardId);
        board.setName("작성자");
        board.setSubject("제목");
        board.setContent("내용");

        when(boardRepository.findById(boardId)).thenReturn(Optional.of(board));

        BoardDto result = boardService.getBoard(boardId);

        assertNotNull(result);
        assertEquals(boardId, result.getId());
        assertEquals("작성자", result.getName());
        assertEquals("제목", result.getSubject());
        assertEquals("내용", result.getContent());
    }

    @Test
    @DisplayName("조회수 증가")
    void increaseHitCount() {
        Long boardId = 1L;
        Board board = new Board();
        board.setId(boardId);
        board.setHitcount(5);

        when(boardRepository.findById(boardId)).thenReturn(Optional.of(board));

        boardService.increaseHitCount(boardId);

        assertEquals(6, board.getHitCount());
        verify(boardRepository, times(1)).findById(any());
    }

    @Test
    @DisplayName("게시글 삭제하기")
    void deletePost() {
        Long boardId = 1L;

        when(boardRepository.findById(boardId)).thenReturn(Optional.empty());

        boardService.deletePost(boardId);

        assertThrows(EntityNotFoundException.class, () -> boardService.getBoard(boardId));
        verify(boardRepository, times(1)).deleteById(boardId);
    }
}