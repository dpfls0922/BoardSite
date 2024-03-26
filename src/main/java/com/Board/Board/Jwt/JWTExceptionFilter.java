package com.Board.Board.Jwt;

import com.Board.Board.Exception.ErrorResponse;
import com.Board.Board.Exception.ErrorUtils;
import com.Board.Board.Jwt.JWTUtil;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JWTExceptionFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (JwtException e){
            ErrorUtils.getErrorResponse(response, HttpStatus.UNAUTHORIZED, ErrorResponse.of(e.getMessage()));
            jwtUtil.deleteCookie(request, response, "jwt");
        }
    }
}