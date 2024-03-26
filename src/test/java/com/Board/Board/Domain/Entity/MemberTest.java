package com.Board.Board.Domain.Entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MemberEntity 생성 테스트")
class MemberTest {

    @Nested
    @DisplayName("프로필 수정 메소드")
    class updateProfile {

        @Test
        @DisplayName("유저 이름 수정")
        public void testUpdateUsername() {

            Member member = Member.builder()
                    .username("김유저")
                    .userid("testUser")
                    .password("password123")
                    .role(MemberRole.USER)
                    .build();

            member.updateUsername("새로운 이름");

            assertEquals("새로운 이름", member.getUsername());
        }

        @Test
        @DisplayName("유저 비밀번호 수정")
        void updatePassword() {
            Member member = Member.builder()
                    .username("김유저")
                    .userid("testUser")
                    .password("oldPassword")
                    .role(MemberRole.USER)
                    .build();

            member.updatePassword("newPassword123");

            assertEquals("newPassword123", member.getPassword());
        }
    }
}