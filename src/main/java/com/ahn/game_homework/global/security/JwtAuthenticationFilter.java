package com.ahn.game_homework.global.security;

import com.ahn.game_homework.domain.user.entity.User;
import com.ahn.game_homework.domain.user.repository.UserRepository;
import com.ahn.game_homework.global.error.ErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

/**
 * HTTP 요청마다 JWT를 검증하고 SecurityContext에 인증 정보를 설정하는 필터.
 * OncePerRequestFilter: 같은 요청에서 이 필터가 딱 한 번만 실행되도록 보장한다.
 * 구현 예정: 헤더에서 토큰 추출 → 검증 → userId 파싱 → SecurityContextHolder에 인증 객체 저장.
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String BEARER_PREFIX = "Bearer ";

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try{
            String token = resolveToken(request);

            if(token != null){
                jwtTokenProvider.validateToken(token);
                Long userId = jwtTokenProvider.getUserId(token);
                User user = userRepository.findById(userId)
                        .orElseThrow(()->new JwtAuthenticationException(ErrorCode.UNAUTHORIZED));

                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        user.getId(),
                        null,
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                        );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        } catch (JwtAuthenticationException e){
            SecurityContextHolder.clearContext();
            jwtAuthenticationEntryPoint.commence(request, response, e);
        }
    }

    private String resolveToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(authorizationHeader != null && authorizationHeader.startsWith(BEARER_PREFIX)){
            return authorizationHeader.substring(BEARER_PREFIX.length());
        }

        return null;
    }
}
