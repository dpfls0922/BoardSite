package com.Board.Board.Dto;

import com.Board.Board.Domain.Entity.Board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BoardDto 생성 테스트")
class BoardDtoTest {

    @Test
    @DisplayName("toEntity 테스트")
    void toEntity() {
        // given
        BoardDto boardDto = BoardDto.builder()
                .id(1L)
                .writer("이예린")
                .email("yerin0922@gmail.com")
                .subject("제목")
                .content("내용")
                .hitCount(0)
                .build();

        // when
        Board board = boardDto.toEntity();

        // then
        assertEquals("com.Board.Board.Domain.Entity.Board", board.getClass().getName()); // 속한 클래스 확인
        assertNotNull(board);
        assertEquals(boardDto.getWriter(), board.getWriter());
        assertEquals(boardDto.getSubject(), board.getSubject());
        assertEquals(boardDto.getContent(), board.getContent());
    }

    @Test
    @DisplayName("builder 테스트")
    void builder() {
        // given
        Long id = 1L;
        String name = "이예린";
        String email = "yerin0922@gmail.com";
        String subject = "제목";
        String content = "내용";
        int hitCount = 0;
        // when
        BoardDto boardDto = BoardDto.builder()
                .id(id)
                .writer(name)
                .email(email)
                .subject(subject)
                .content(content)
                .hitCount(hitCount)
                .build();
        // then
        assertEquals("com.Board.Board.Dto.BoardDto", boardDto.getClass().getName()); // 속한 클래스 확인
        assertNotNull(boardDto);
        assertEquals(id, boardDto.getId());
        assertEquals(name, boardDto.getWriter());
        assertEquals(email, boardDto.getEmail());
        assertEquals(subject, boardDto.getSubject());
        assertEquals(content, boardDto.getContent());
        assertEquals(hitCount, boardDto.getHitCount());
    }
}