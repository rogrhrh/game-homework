package com.ahn.game_homework.domain.routine.repository;

import com.ahn.game_homework.domain.routine.entity.Routine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutineRepository extends JpaRepository<Routine, Long> {
}
