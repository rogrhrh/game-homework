package com.ahn.game_homework.domain.routine.repository;

import com.ahn.game_homework.domain.routine.entity.RoutineItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutineItemRepository extends JpaRepository<RoutineItem, Long> {
}
