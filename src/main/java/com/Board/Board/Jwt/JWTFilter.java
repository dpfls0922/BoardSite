package com.Board.Board.Jwt;

import com.Board.Board.Domain.Entity.Member;
import com.Board.Board.Domain.Entity.MemberRole;
import com.Board.Board.Dto.MemberDetails;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtUtil.resolveToken(request);

        if (token != null) {
            try {
                jwtUtil.isExpired(token);
            } catch(ExpiredJwtException e) {
                logger.info("token expired");
                throw new JwtException("만료된 JWT 토큰입니다", e);
            }

            String username = jwtUtil.getUsername(token);
            MemberRole role;
            if ("admin".equals(username)) {
                role = MemberRole.ADMIN;
            } else {
                role = MemberRole.USER;
            }
            Member member = Member.builder()
                    .userid(username)
                    .password("temppassword")
                    .role(role)
                    .build();

            MemberDetails memberDetails = new MemberDetails(member);

            Authentication authToken = new UsernamePasswordAuthenticationToken(memberDetails, null, memberDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        /*다음 필터 진행*/
        filterChain.doFilter(request, response);
    }
}
