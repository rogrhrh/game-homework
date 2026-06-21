package com.ahn.game_homework.domain.user.repository;

import com.ahn.game_homework.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// User CRUD Repository. Spring Data JPA가 구현체를 자동 생성한다.
public interface UserRepository extends JpaRepository<User, Long> {

    // 메서드 이름 규칙으로 SQL 자동 생성: SELECT * FROM users WHERE email = ?
    // Optional로 반환해 null 가능성을 명시 → 호출자가 .orElseThrow()로 처리해야 함.
    Optional<User> findByEmail(String email);
}
