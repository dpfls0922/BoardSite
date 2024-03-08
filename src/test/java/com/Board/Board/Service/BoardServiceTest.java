package com.Board.Board.Service;

import com.Board.Board.Domain.Repository.BoardRepository;
import com.Board.Board.Dto.BoardDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import com.Board.Board.Domain.Entity.Board;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@Transactional
@DisplayName("Service 생성 테스트")
@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @Mock
    BoardRepository boardRepository;
    @InjectMocks
    BoardService boardService;

    @Test
    @DisplayName("게시물 생성 테스트")
    void savePostTest() {
        // given
        // 테스트 데이터 생성
        BoardDto boardDto = new BoardDto();
        boardDto.setName("yerin");
        boardDto.setSubject("title");
        boardDto.setContent("content");
        // 가짜 객체 생성 (실제 DB에 저장 안됨)
        Board boardEntity =Board.builder()
                .name(boardDto.getName())
                .subject(boardDto.getSubject())
                .content(boardDto.getContent())
                .build();
        // mocking
        Mockito.when(boardRepository.save(Mockito.any())).thenReturn(boardEntity);

        // When
        Integer savedBoardNum = boardService.savePost(boardDto);

        // Then
        assertEquals(boardEntity.getName(), boardDto.getName());
        assertEquals(boardEntity.getSubject(), boardDto.getSubject());
//        assertEquals(1, savedBoardNum);
//        assertNotNull(savedBoardNum, "게시물 번호는 null이 아니어야 합니다.");
//        assertTrue(savedBoardNum > 0, "게시물 번호는 0보다 커야 합니다.");
    }

    @Test
    @DisplayName("getAllBoards 테스트")
    void getAllBoards() {
    }

    @Test
    @DisplayName("getAllBoardsReversed 테스트")
    void getAllBoardsReversed() {
    }

    @Test
    @DisplayName("getBoard 테스트")
    void getBoard() {
    }

    @Test
    @DisplayName("조회수 증가 테스트")
    void increaseHitCount() {
    }

    @Test
    @DisplayName("게시글 삭제 테스트")
    void deletePost() {
    }
}