package com.Board.Board.Service;

import com.Board.Board.Domain.Entity.Board;
import com.Board.Board.Domain.Entity.Member;
import com.Board.Board.Domain.Entity.MemberRole;
import com.Board.Board.Domain.Repository.BoardRepository;
import com.Board.Board.Domain.Repository.MemberRepository;
import com.Board.Board.Dto.MemberDto;
import com.Board.Board.Dto.MemberSignupDto;
import com.Board.Board.Jwt.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("MemberService 테스트")
@ExtendWith(MockitoExtension.class)
class MemberServiceTest {
    @Mock
    private MemberRepository memberRepository;

    @Mock
    private BoardRepository boardRepository;

    @Mock
    private JWTUtil jwtUtil;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    private MemberService memberService;


    @Test
    @DisplayName("회원 가입 테스트")
    void joinTest() {
        // Given
        MemberSignupDto signupDto = new MemberSignupDto();
        signupDto.setUserid("testUser");
        signupDto.setPassword1("password123");
        signupDto.setEmail("test@naver.com");

        when(memberRepository.save(any(Member.class))).thenReturn(new Member());

        // When
        Long memberId = memberService.join(signupDto);

        // Then
        assertNotNull(memberId);
        verify(memberRepository, times(1)).save(any(Member.class));
    }

    @Test
    @DisplayName("회원 정보 조회 테스트")
    void findMemberTest() {
        // Given
        String userid = "testUser";
        Member member = Member.builder().
                userid(userid)
                .build();
        when(memberRepository.findByUserid(userid)).thenReturn(Optional.of(member));

        // When
        MemberDto memberDto = memberService.findMember(userid);

        // Then
        assertNotNull(memberDto);
        assertEquals(memberDto.getUserid(), userid);
    }

    @Test
    @DisplayName("회원 정보 수정 테스트")
    void updateMemberTest() {
        // Given
        String userid = "testUser";
        String username = "수정된 이름";
        String password = "수정된 비밀번호";
        MemberRole role = MemberRole.USER;

        MemberDto memberDto = new MemberDto();
        memberDto.setUserid(userid);
        memberDto.setUsername(username);
        memberDto.setPassword(password);

        Member member = Member.builder()
                .userid(userid)
                .username("기존 이름")
                .password("기존 비밀번호")
                .role(role)
                .build();

        HttpServletResponse response = mock(HttpServletResponse.class);
        when(memberRepository.findByUserid(userid)).thenReturn(Optional.of(member));
        when(memberRepository.save(any(Member.class))).thenReturn(member);

        // When
        Long updatedMemberId = memberService.updateMember(memberDto, response);

        // Then
        assertNotNull(updatedMemberId);
        assertEquals(username, member.getUsername());
        assertNotEquals(password, member.getPassword());
        verify(jwtUtil, times(1)).createJwt(userid, role.getValue());
        verify(response, times(1)).addHeader(eq("Authorization"), startsWith("Bearer "));
    }

    @Nested
    @DisplayName("회원 탈퇴 테스트")
    public class withdrawalTest {
        @Test
        @DisplayName("올바른 비밀번호 입력")
        void withdrawalSuccessTest() {
            // Given
            String userid = "testUser";
            String password = "password";

            Member member = Member.builder().
                    username(userid).
                    password(password)
                    .build();

            List<Board> boards = new ArrayList<>();
            boards.add(new Board());
            boards.add(new Board());

            HttpServletRequest request = mock(HttpServletRequest.class);
            HttpServletResponse response = mock(HttpServletResponse.class);

            when(memberRepository.findByUserid(userid)).thenReturn(Optional.of(member));
            when(boardRepository.findByMember(member)).thenReturn(boards);
            when(passwordEncoder.matches(password, member.getPassword())).thenReturn(true);

            // When
            boolean result = memberService.withdrawal(userid, password, request, response);

            // Then
            assertTrue(result);
            verify(memberRepository, times(1)).delete(member);
            verify(jwtUtil, times(1)).deleteCookie(any(HttpServletRequest.class), any(HttpServletResponse.class), anyString());
            assertEquals("(탈퇴한 회원)", boards.get(0).getWriter());
            assertNull(boards.get(0).getMember());
            assertEquals("(탈퇴한 회원)", boards.get(1).getWriter());
            assertNull(boards.get(1).getMember());
        }

        @Test
        @DisplayName("잘못된 비밀번호 입력")
        void withdrawalFailureWrongPasswordTest() {
            // Given
            String userid = "testUser";
            String password = "password";

            Member member = Member.builder().
                    username(userid).
                    password(password)
                    .build();

            HttpServletRequest request = mock(HttpServletRequest.class);
            HttpServletResponse response = mock(HttpServletResponse.class);

            when(memberRepository.findByUserid(userid)).thenReturn(Optional.of(member));
            when(passwordEncoder.matches("wrongPassword", member.getPassword())).thenReturn(false);

            // When
            boolean result = memberService.withdrawal(userid, "wrongPassword", request, response);

            // Then
            assertFalse(result);
            verify(memberRepository, never()).delete(any(Member.class));
            verify(jwtUtil, never()).deleteCookie(any(HttpServletRequest.class), any(HttpServletResponse.class), anyString());
        }

        @Test
        @DisplayName("아이디가 존재하지 않는 경우")
        void withdrawalFailureUserIdNotFoundTest() {
            // Given
            String userid = "nonExistingUser";
            String password = "password";

            HttpServletRequest request = mock(HttpServletRequest.class);
            HttpServletResponse response = mock(HttpServletResponse.class);

            when(memberRepository.findByUserid(userid)).thenReturn(Optional.empty());

            // When & Then
            assertThrows(UsernameNotFoundException.class, () -> memberService.withdrawal(userid, password, request, response));
            verify(memberRepository, never()).delete(any(Member.class));
            verify(jwtUtil, never()).deleteCookie(any(HttpServletRequest.class), any(HttpServletResponse.class), anyString());
        }
    }
}