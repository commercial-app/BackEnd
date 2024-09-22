package com.example.server.service;

import com.example.server.common.exception.CustomException;
import com.example.server.common.exception.ErrorCode;
import com.example.server.entity.MissionCategory;
import com.example.server.repository.CategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;

    @Transactional(readOnly = true)
    public MissionCategory findByCategoryName(String name) {
        return categoriesRepository.findByName(name)
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MISSION));
    }
}
