package com.ahn.game_homework.domain.routine.service;

import com.ahn.game_homework.domain.routine.repository.RoutineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoutineService {

    private final RoutineRepository routineRepository;

    private String generateSlug(String title){
        String base = title.trim()
                .toLowerCase()
                .replaceAll("[^a-z0-9가-힣\\s-]", "")
                .replaceAll("\\s+", "-")
                .replaceAll("-{2,}", "-")
                .replaceAll("^-|-$", "");

        if(base.isBlank()){
            base = "routine";
        }

        String slug = base;
        int suffix = 2;
        while (routineRepository.existsBySlug(slug)){
            slug = base + "-" + suffix++;
        }
        return slug;
    }
}
