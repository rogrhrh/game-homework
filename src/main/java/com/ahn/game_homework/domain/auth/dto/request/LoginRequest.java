package com.ahn.game_homework.domain.auth.dto.request;

// 로그인 요청 DTO. 형식 검사 없이 Service에서 DB 조회 결과로 성공/실패를 판단한다.
public record LoginRequest(
        String email,
        String password
) {
}
