package com.ahn.game_homework.domain.auth.dto.request;

public record LoginRequest(
        String email,
        String password
) {
}
