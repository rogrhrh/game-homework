package com.ahn.game_homework.domain.routine.enums;

// 루틴 아이템 우선순위. UI 색상 구분, 필터링, 완료율 가중치 등에 활용.
public enum Priority {
    HIGH,   // 핵심 숙제 (레이드, 주간 보스)
    MEDIUM, // 가능하면 하는 숙제 (일일 의뢰)
    LOW     // 시간 남을 때 (도전 콘텐츠)
}
