package com.ahn.game_homework.global.error;

import lombok.Getter;

// 비즈니스 로직 예외. JwtAuthenticationException과 달리 도메인 로직 실패(중복 이메일, 잘못된 인증 등)에 사용.
@Getter
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
