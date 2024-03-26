package com.Board.Board.Exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {
    private final HttpStatus status;
    private final String message;

    public static ErrorResponse of(String errorMessage) {
        return new ErrorResponse(HttpStatus.UNAUTHORIZED, errorMessage);
    }
}
