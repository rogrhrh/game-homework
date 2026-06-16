package com.ahn.game_homework.domain.routine.entity;

import com.ahn.game_homework.domain.routine.enums.Priority;
import com.ahn.game_homework.domain.routine.enums.RepeatType;
import com.ahn.game_homework.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoutineItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id", nullable = false)
    private Routine routine;

    @Column(nullable = false)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RepeatType repeatType;

    @Column(nullable = false)
    private Integer targetCount;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private Boolean essential;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;

    private Integer estimatedMinutes;

    private String conditionText;
    private String rewardText;

    @Column(nullable = false)
    private Integer sortOrder;

    private LocalDateTime deletedAt;
}
