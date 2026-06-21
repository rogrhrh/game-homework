package com.ahn.game_homework.global.security;

import com.ahn.game_homework.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class JwtAuthenticationException extends RuntimeException{
    private final ErrorCode errorCode;

    public JwtAuthenticationException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
