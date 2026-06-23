package com.ahn.game_homework.domain.routine.entity;

import com.ahn.game_homework.domain.routine.enums.Visibility;
import com.ahn.game_homework.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/** 게임 루틴(체크리스트 묶음) 엔티티. RoutineItem들의 컨테이너 역할. */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // JPA용 기본 생성자. 외부에서 직접 호출 불가.
@AllArgsConstructor(access = AccessLevel.PRIVATE)   // @Builder가 내부적으로 사용하는 생성자.
@Builder
public class Routine extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User 엔티티를 직접 참조하지 않고 ID만 저장 — 도메인 간 결합을 느슨하게 유지
    @Column(nullable = false)
    private Long authorId;

    @Column(nullable = false)
    private String gameName;

    @Builder.Default
    @Column(nullable = false)
    private Integer dailyResetHour = 0; // 일일 초기화 시각 (0~23시)

    @Builder.Default
    @Column(nullable = false)
    private Integer weeklyResetDayOfWeek = 1; // 주간 초기화 요일 (1=월 ~ 7=일, ISO 표준)

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false, unique = true)
    private String slug; // URL용 고유 식별자. /routines/{slug} 형태로 사용.

    @Enumerated(EnumType.STRING) // DB에 숫자 대신 "PUBLIC" 문자열로 저장 (순서 변경 안전)
    @Column(nullable = false)
    private Visibility visibility;

    // cascade=ALL: 루틴 삭제 시 아이템도 함께 삭제.
    // orphanRemoval: items 리스트에서 제거하면 DB에서도 DELETE됨.
    @Builder.Default
    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoutineItem> items = new ArrayList<>();

    // 소프트 삭제용. null=활성, 값 있음=삭제됨. 조회 시 WHERE deleted_at IS NULL 조건 추가.
    private LocalDateTime deletedAt;

    // 정적 팩토리 메서드: slug는 Service에서 생성해 결과만 받는다. authorId는 JWT에서 추출된 값.
    public static Routine create(
            Long authorId,
            String gameName,
            Integer dailyResetHour,
            Integer weeklyResetDayOfWeek,
            String title,
            String description,
            String slug,
            Visibility visibility
    ) {
        return Routine.builder()
                .authorId(authorId)
                .gameName(gameName)
                .dailyResetHour(dailyResetHour)
                .weeklyResetDayOfWeek(weeklyResetDayOfWeek)
                .title(title)
                .description(description)
                .slug(slug)
                .visibility(visibility)
                .build();
    }

    public void softDelete() {
        this.deletedAt = LocalDateTime.now();
    }

    public void update(String title, String description, String gameName,
                       Integer dailyResetHour, Integer weeklyResetDayOfWeek, Visibility visibility) {
        this.title = title;
        this.description = description;
        this.gameName = gameName;
        this.dailyResetHour = dailyResetHour;
        this.weeklyResetDayOfWeek = weeklyResetDayOfWeek;
        this.visibility = visibility;
    }
}
