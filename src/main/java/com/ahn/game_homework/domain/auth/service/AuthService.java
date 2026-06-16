package com.ahn.game_homework.domain.auth.service;

import com.ahn.game_homework.domain.auth.dto.request.SignupRequest;
import com.ahn.game_homework.domain.auth.dto.response.SignupResponse;
import com.ahn.game_homework.domain.user.entity.User;
import com.ahn.game_homework.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public SignupResponse signup(SignupRequest request)
    {
        String encodedPassword = passwordEncoder.encode((request.password()));
        User user =  User.create(request.email(), encodedPassword, request.nickname());
        User savedUser = userRepository.save(user);

        return SignupResponse.from(savedUser);
    }
}
