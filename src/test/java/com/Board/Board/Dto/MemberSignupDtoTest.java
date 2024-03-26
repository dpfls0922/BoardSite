package com.Board.Board.Dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MemberSignupDtoTest 생성 테스트")
class MemberSignupDtoTest {

    private static Validator validator;
    @BeforeAll
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    @DisplayName("유효한 회원가입")
    public void testValidMemberSignupDto() {
        MemberSignupDto dto = new MemberSignupDto();
        dto.setEmail("user@naver.com");
        dto.setUsername("김유저");
        dto.setUserid("testUser");
        dto.setPassword1("password123");
        dto.setPassword2("password123");

        Set<ConstraintViolation<MemberSignupDto>> violations = validator.validate(dto);

        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("유효하지 않은 회원가입")
    public void testInvalidMemberSignupDto() {
        MemberSignupDto dto = new MemberSignupDto();
        dto.setEmail("user"); // 유효 x
        dto.setUsername("a"); // 유효 x
        dto.setUserid("");    // 유효 x
        dto.setPassword1("password123");
        dto.setPassword2("password456");  // 유효 x (Match x)

        Set<ConstraintViolation<MemberSignupDto>> violations = validator.validate(dto);

        assertEquals(4, violations.size()); // 4개의 제약 위반
    }
}