package com.ahn.game_homework.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 인증 이후의 User 관련 기능 담당 (프로필 조회/수정, 탈퇴 등). AuthService와 관심사 분리.
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
}
