package com.Board.Board.Controller;

import com.Board.Board.Dto.MemberSignupDto;
import com.Board.Board.Jwt.JWTUtil;
import com.Board.Board.Service.MemberService;
import com.Board.Board.Validator.CheckSignupValidator;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@MockBean(JpaMetamodelMappingContext.class)
@WebMvcTest(MemberController.class)
@DisplayName("MemberController 테스트")
@WithMockUser(username = "user", roles = "USER")
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @MockBean
    private CheckSignupValidator checkSignupValidator;

    @MockBean
    private JWTUtil jwtUtil;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new MemberController(memberService, checkSignupValidator)).build();
    }

    @Nested
    @DisplayName("회원가입 테스트")
    class signupTest {
        @Test
        @DisplayName("Get - 회원가입 페이지 불러오기")
        void signupPageTest() throws Exception {
            mockMvc.perform(get("/user/signup"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("member/signup"));
        }

        @Test
        @DisplayName("Post - 회원가입 성공")
        void signupSuccessTest() throws Exception {
            MemberSignupDto dto = new MemberSignupDto();

            dto.setEmail("user@naver.com");
            dto.setUsername("김유저");
            dto.setUserid("testUser");
            dto.setPassword1("password123");
            dto.setPassword2("password123");

            when(memberService.join(any(MemberSignupDto.class))).thenReturn(1L);

            mockMvc.perform(post("/user/signup")
                            .flashAttr("memberSignupDto", dto)
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .with(csrf()))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/list"));
        }

        @Test
        @DisplayName("Post - 회원가입 실패")
        void signupFailureUserIdDuplicateTest() throws Exception {
            MemberSignupDto dto = new MemberSignupDto();

            // 아이디가 이미 존재하는 경우를 가정하여 테스트
            when(memberService.join(any(MemberSignupDto.class))).thenThrow(new RuntimeException("아이디 중복 오류"));

            mockMvc.perform(post("/user/signup")
                            .flashAttr("memberSignupDto", dto)
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .with(csrf()))
                    .andExpect(status().isOk())
                    .andExpect(view().name("member/signup"));
        }

    }

}