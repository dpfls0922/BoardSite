package com.Board.Board.Controller;

import com.Board.Board.Domain.Entity.Board;
import com.Board.Board.Dto.BoardDto;
import com.Board.Board.Service.BoardService;
import com.Board.Board.Jwt.JWTUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@MockBean(JpaMetamodelMappingContext.class)
@WebMvcTest(BoardController.class)
@DisplayName("BoardController 생성 테스트")
@WithMockUser(username = "user", roles = "USER")
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;   // 서블릿 컨테이너 사용 x
                               // 테스트용으로 Mvc 기능 사용할 수 있음
    @MockBean
    BoardService boardService;

    @MockBean
    private JWTUtil jwtUtil;


    private List<Board> dummyBoards;
    @BeforeEach
    void beforeEach(){
        dummyBoards = Arrays.asList(
                new Board(1, "이메일 1", "제목 1", "내용 1"),
                new Board(2,  "이메일 2", "제목 2", "내용 2")
        );
    }

    @Test
    @DisplayName("목록 불러오기")
    void listTest() throws Exception {
        when(boardService.getAllBoardsReversed()).thenReturn(dummyBoards);

        mockMvc.perform(get("/list"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("boards", hasSize(dummyBoards.size())))
                .andExpect(view().name("board/list"));
    }

    @Test
    @DisplayName("글 상세보기")
    void detailTest() throws Exception {
        int boardId = 1;
        BoardDto boardDto = new BoardDto();
        when(boardService.getBoard(boardId)).thenReturn(boardDto);

        mockMvc.perform(get("/list/{id}", boardId))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("board"))
                .andExpect(view().name("board/article"));
    }


    @Nested
    @DisplayName("글 등록하기")
    class registerTest {
        @Test
        @DisplayName("Get")
        void registerGet() throws Exception {
            mockMvc.perform(get("/register"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("board/created"));
        }

        @Test
        @DisplayName("Post")
        void registerPost() throws Exception {
            mockMvc.perform(post("/register")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED).with(csrf())  // application/x-www-form-urlencoded
                    .param("title", "새로운 제목")
                    .param("content", "새로운 내용"))
                    .andExpect(status().is3xxRedirection())             // 리다이렉션 응답 코드
                    .andExpect(redirectedUrl("/list"));

            verify(boardService, times(1)).savePost(any(BoardDto.class), eq("user"));
        }
    }

    @Nested
    @DisplayName("글 수정하기")
    class editTest {
        @DisplayName("Get")
        @Test
        void editGet() throws Exception {
            int boardId = 1;
            BoardDto boardDto = new BoardDto();
            boardDto.setNum(boardId);
            boardDto.setName("user");

            when(boardService.getBoard(boardId)).thenReturn(boardDto);

            mockMvc.perform(get("/list/edit/{id}", boardId))
                    .andExpect(status().isOk())
                    .andExpect(model().attributeExists("board"))
                    .andExpect(view().name("board/edit"));
        }

        @Test
        @DisplayName("Put")
        void editPut() throws Exception {
            int boardId = 1;
            String loggedInUserId = "user";

            mockMvc.perform(put("/list/edit/{id}", boardId)
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED).with(csrf())
                            .param("name", "수정한 작성자")
                            .param("title", "수정한 제목")
                            .param("content", "수정한 내용"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/list/1"));

            verify(boardService, times(1)).updatePost(eq(boardId), any(BoardDto.class));
        }
    }

    @Test
    @DisplayName("글 삭제하기")
    void deleteBoardTest() throws Exception {
        int boardId = 1;
        String loggedInUserId = "user";
        String postUserId = "user";

        when(boardService.findUserIdByPostId(boardId)).thenReturn(postUserId);

        mockMvc.perform(delete("/list/remove/{id}", boardId)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/list"));

        verify(boardService, times(1)).deletePost(boardId);
    }
}