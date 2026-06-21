package com.ahn.game_homework.global.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * HTTP 요청마다 JWT를 검증하고 SecurityContext에 인증 정보를 설정하는 필터.
 * OncePerRequestFilter: 같은 요청에서 이 필터가 딱 한 번만 실행되도록 보장한다.
 * 구현 예정: 헤더에서 토큰 추출 → 검증 → userId 파싱 → SecurityContextHolder에 인증 객체 저장.
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        filterChain.doFilter(request, response);
    }
}
