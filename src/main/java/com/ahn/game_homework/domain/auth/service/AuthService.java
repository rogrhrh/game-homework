package com.ahn.game_homework.domain.auth.service;

import com.ahn.game_homework.domain.auth.dto.request.LoginRequest;
import com.ahn.game_homework.domain.auth.dto.request.SignupRequest;
import com.ahn.game_homework.domain.auth.dto.response.LoginResponse;
import com.ahn.game_homework.domain.auth.dto.response.SignupResponse;
import com.ahn.game_homework.domain.user.entity.User;
import com.ahn.game_homework.domain.user.repository.UserRepository;
import com.ahn.game_homework.global.error.BusinessException;
import com.ahn.game_homework.global.error.ErrorCode;
import com.ahn.game_homework.global.security.JwtTokenProvider;
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
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public SignupResponse signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new BusinessException(ErrorCode.DUPLICATE_EMAIL);
        }

        String encodedPassword = passwordEncoder.encode(request.password());
        User user = User.create(request.email(), encodedPassword, request.nickname());
        User savedUser = userRepository.save(user);

        return SignupResponse.from(savedUser);
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_CREDENTIALS));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BusinessException(ErrorCode.INVALID_CREDENTIALS);
        }

        String accessToken = jwtTokenProvider.createAccessToken(user.getId());
        return LoginResponse.of(accessToken);
    }
}
