package com.ahn.game_homework.domain.routine.entity;

import com.ahn.game_homework.domain.routine.enums.Priority;
import com.ahn.game_homework.domain.routine.enums.RepeatType;
import com.ahn.game_homework.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/** 루틴 내 개별 숙제 항목 엔티티. 예: "카오스 던전 2회", "가디언 토벌 2회". */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoutineItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // LAZY 로딩: routine.getTitle() 접근 시점에 SELECT 실행. 불필요한 JOIN 방지.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id", nullable = false)
    private Routine routine;

    @Column(nullable = false)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RepeatType repeatType; // DAILY, WEEKLY, MONTHLY, EVENT, NONE

    @Column(nullable = false)
    private Integer targetCount; // 완료 인정 횟수. 예: 카오스 던전 = 2

    @Column(nullable = false)
    private String unit; // 횟수 단위. 예: "회", "개"

    @Column(nullable = false)
    private Boolean essential; // true이면 구독자가 삭제 불가 처리 가능

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority; // HIGH / MEDIUM / LOW

    private Integer estimatedMinutes; // 예상 소요 시간(분). null 가능.

    private String conditionText; // 수행 조건 설명. 예: "레헬린 클리어 후 가능"
    private String rewardText;    // 보상 설명. 예: "레헬린 조각 2개"

    @Column(nullable = false)
    private Integer sortOrder; // 루틴 내 표시 순서. 드래그 앤 드롭 시 업데이트됨.

    private LocalDateTime deletedAt; // 소프트 삭제용. null=활성, 값 있음=삭제됨.
}
