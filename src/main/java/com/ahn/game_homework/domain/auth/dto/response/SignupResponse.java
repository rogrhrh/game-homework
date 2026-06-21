package com.ahn.game_homework.domain.auth.dto.response;

import com.ahn.game_homework.domain.user.entity.User;

/**
 * 회원가입 응답 DTO.
 * 엔티티를 그대로 반환하지 않고 DTO로 변환 — 비밀번호 같은 민감 정보가 노출되지 않도록.
 */
public record SignupResponse(
        Long userId,
        String email,
        String nickname
) {
    // 엔티티 → DTO 변환. Service에서 SignupResponse.from(user) 한 줄로 사용.
    public static SignupResponse from(User user) {
        return new SignupResponse(
                user.getId(),
                user.getEmail(),
                user.getNickname()
        );
    }
}
