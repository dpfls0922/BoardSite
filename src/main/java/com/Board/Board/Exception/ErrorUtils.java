package com.Board.Board.Exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import static org.eclipse.jdt.internal.compiler.util.Util.UTF_8;

@Getter
@ToString
public class ErrorUtils {
    private static final ObjectMapper objMapper = new ObjectMapper();

    public static void getErrorResponse(HttpServletResponse response, HttpStatus httpStatus, ErrorResponse errorResponse) throws IOException {
        response.setStatus(httpStatus.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(UTF_8);
        String error = objMapper.writeValueAsString(errorResponse);
        response.getWriter().write(error);
    }
}