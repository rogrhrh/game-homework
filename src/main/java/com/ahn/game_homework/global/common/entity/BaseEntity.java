package com.ahn.game_homework.global.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 모든 엔티티가 상속받는 공통 기반 클래스.
 * @MappedSuperclass: 이 클래스의 테이블은 생성되지 않고, 자식 엔티티 테이블에 컬럼이 추가된다.
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false, nullable = false) // INSERT 시 자동 세팅, 이후 변경 불가
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false) // UPDATE마다 자동 갱신
    private LocalDateTime updatedAt;
}
