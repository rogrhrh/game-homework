package com.ahn.game_homework.domain.auth.dto.response;

import com.ahn.game_homework.domain.user.entity.User;

public record SignupResponse(
        Long userId,
        String email,
        String nickname
) {
    public static SignupResponse from(User user){
        return new SignupResponse(
                user.getId(),
                user.getEmail(),
                user.getNickname()
        );
    }
}

