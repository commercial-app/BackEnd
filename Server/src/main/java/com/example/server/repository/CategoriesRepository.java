package com.example.server.repository;

import com.example.server.entity.MissionCategory;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoriesRepository extends JpaRepository<MissionCategory, Long> {
    Optional<MissionCategory> findByName(String name);
}
