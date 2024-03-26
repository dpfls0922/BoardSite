package com.Board.Board.Domain.Entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BoardEntity 생성 테스트")
class BoardTest {

    @Test
    @DisplayName("Getter 및 Setter 테스트")
    void testGettersAndSetters() {
        // given
        Board board = new Board();
        // when
        board.setNum(1);
        board.setName("yerin");
        board.setSubject("title");
        board.setContent("content");
        board.setHitcount(10);
        board.setCreatedDate(LocalDateTime.now());
        board.setUpdatedDate(LocalDateTime.now());
        // then
        assertEquals(1, board.getNum());
        assertEquals("yerin", board.getName());
        assertEquals("title", board.getSubject());
        assertEquals("content", board.getContent());
        assertEquals(10, board.getHitCount());
        assertNotNull(board.getCreatedDate());
        assertNotNull(board.getUpdatedDate());
    }


    @Test
    @DisplayName("Equals 및 HashCode 테스트")
    void testEqualsAndHashCode() {
        // given, when
        Board board1 = new Board(1, "test", "subject1", "content1");
        Board board2 = board1;

        // then
        assertEquals(board1, board2);  // 객체의 내부 데이터나 상태가 동일
        board1.setName("example");
        assertEquals(board2.getName(), "example");
        assertEquals(board1.hashCode(), board2.hashCode());  // 동등한 객체는 동일한 해시 코드를 가짐
    }

    @Test
    @DisplayName("toString() 테스트")
    void testToString() {
        // given
        LocalDateTime currentDateTime = LocalDateTime.now();
        Board board = new Board();
        // when
        board.setNum(1);
        board.setName("yerin");
        board.setCreatedDate(currentDateTime);
        board.setUpdatedDate(currentDateTime);
        String expectedToString = "Board(num=1, name=yerin, subject=null, " + "content=null, hitcount=0, " +
                "member=null, createdDate=" + currentDateTime + ", updatedDate=" + currentDateTime + ")";
        // then
        assertEquals(expectedToString, board.toString());
    }

    @Test
    @DisplayName("Builder 테스트")
    void builder() {
        // given, when
        Board board = Board.builder().
            num(1).
            name("yerin").
            subject("title").
            content("content").build();
        // then
        assertEquals(1, board.getNum());
        assertEquals("yerin", board.getName());
        assertEquals("title", board.getSubject());
        assertEquals("content", board.getContent());
    }
}