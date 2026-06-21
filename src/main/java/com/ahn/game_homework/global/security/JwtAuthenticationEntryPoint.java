package com.ahn.game_homework.global.security;

import com.ahn.game_homework.global.error.ErrorCode;
import com.ahn.game_homework.global.error.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException{
        writeErrorResponse(response, ErrorCode.UNAUTHORIZED);
    }

    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            JwtAuthenticationException jwtException
    ) throws IOException {
        writeErrorResponse(response, jwtException.getErrorCode());
    }

    private void writeErrorResponse(
            HttpServletResponse response,
            ErrorCode errorCode) throws IOException {
        response.setStatus(errorCode.getStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        objectMapper.writeValue(response.getWriter(), ErrorResponse.from(errorCode));
    }
}
