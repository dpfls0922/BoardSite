package com.Board.Board.Domain.Entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MemberRole 테스트")
class MemberRoleTest {

    @Test
    @DisplayName("관리자 역할")
    public void testAdminRole() {
        MemberRole adminRole = MemberRole.ADMIN;

        assertEquals("ROLE_ADMIN", adminRole.getValue());
    }

    @Test
    @DisplayName("유저 역할")
    public void testUserRole() {
        MemberRole userRole = MemberRole.USER;

        assertEquals("ROLE_USER", userRole.getValue());
    }
}