package com.ahn.game_homework.domain.auth.controller;

import com.ahn.game_homework.domain.auth.dto.request.SignupRequest;
import com.ahn.game_homework.domain.auth.dto.response.SignupResponse;
import com.ahn.game_homework.domain.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 인증 관련 엔드포인트. /api/v1/auth */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // @Valid: SignupRequest의 @Email/@Size 등 Bean Validation 실행. 실패 시 400 자동 반환.
    // ResponseEntity.status(201): REST 관례상 생성 성공은 200이 아닌 201 CREATED를 반환한다.
    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(
            @Valid @RequestBody SignupRequest request
    ) {
        SignupResponse response = authService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
