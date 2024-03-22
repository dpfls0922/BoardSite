package com.Board.Board.Jwt;

import com.Board.Board.Domain.Entity.Member;
import com.Board.Board.Domain.Entity.MemberRole;
import com.Board.Board.Dto.MemberDetails;
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

        System.out.println("JWTFilter now");
        String token = jwtUtil.resolveToken(request);

        System.out.println("token : " + token);

        if (token != null) {
            if (jwtUtil.isExpired(token)) {
                System.out.println("token expired");
            }
            String username = jwtUtil.getUsername(token);
            Member member = Member.builder()
                    .userid(username)
                    .password("temppassword")
                    .role(MemberRole.USER)
                    .build();

            MemberDetails memberDetails = new MemberDetails(member);

            Authentication authToken = new UsernamePasswordAuthenticationToken(memberDetails, null, memberDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);

            System.out.println("Authenticated");
            System.out.println("token : " + token);
        }

        /*다음 필터 진행*/
        filterChain.doFilter(request, response);
    }
}
