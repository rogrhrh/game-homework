package com.ahn.game_homework.domain.user.entity;

import com.ahn.game_homework.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * 사용자 엔티티. DB 테이블: users.
 * Setter 없음 — 상태 변경은 명시적 메서드로만. 외부에서 new User() 불가, create()로만 생성.
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // JPA용 기본 생성자. 외부에서 직접 호출 불가.
@AllArgsConstructor(access = AccessLevel.PRIVATE)   // @Builder가 내부적으로 사용하는 생성자.
@Builder
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB AUTO_INCREMENT로 ID 자동 생성
    private Long id;

    @Column(unique = true, nullable = false) // 이메일 중복 가입 방지
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false) // BCrypt 해시된 값만 저장. 평문 절대 안 됨.
    private String password;

    // 정적 팩토리 메서드: "new User()" 대신 의도가 명확한 이름의 메서드로 생성.
    // encodedPassword를 받는 이유: 인코딩은 Service 책임, 엔티티는 그 결과만 받는다.
    public static User create(String email, String encodedPassword, String nickname) {
        return User.builder()
                .email(email)
                .password(encodedPassword)
                .nickname(nickname)
                .build();
    }
}
