package com.ahn.game_homework.domain.auth.service;

import com.ahn.game_homework.domain.auth.dto.request.SignupRequest;
import com.ahn.game_homework.domain.auth.dto.response.SignupResponse;
import com.ahn.game_homework.domain.user.entity.User;
import com.ahn.game_homework.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 인증 비즈니스 로직 (회원가입, 로그인).
 * 클래스 레벨 readOnly = true가 기본. 쓰기 메서드는 @Transactional을 따로 붙여 오버라이드한다.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    // 비밀번호 BCrypt 인코딩 → User 생성 → DB 저장 → 응답 DTO 변환
    @Transactional
    public SignupResponse signup(SignupRequest request) {
        String encodedPassword = passwordEncoder.encode(request.password());
        User user = User.create(request.email(), encodedPassword, request.nickname());
        User savedUser = userRepository.save(user);

        return SignupResponse.from(savedUser);
    }
}
