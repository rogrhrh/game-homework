package com.ahn.game_homework.domain.routine.enums;

// 루틴 아이템 반복 주기. 체크 이력 집계 시 이 값으로 기간을 계산한다.
public enum RepeatType {
    NONE,    // 일회성
    DAILY,   // 매일 (dailyResetHour 기준 초기화)
    WEEKLY,  // 매주 (weeklyResetDayOfWeek 기준 초기화)
    MONTHLY, // 매월
    EVENT    // 기간 한정 이벤트
}
