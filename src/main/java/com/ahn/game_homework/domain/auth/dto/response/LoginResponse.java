package com.ahn.game_homework.domain.auth.dto.response;

/**
 * 로그인 응답 DTO.
 * tokenType은 항상 "Bearer" — 클라이언트는 "Authorization: Bearer {token}" 헤더로 보낸다.
 */
public record LoginResponse(
        String accessToken,
        String tokenType
) {
    public static LoginResponse of(String accessToken) {
        return new LoginResponse(accessToken, "Bearer");
    }
}
