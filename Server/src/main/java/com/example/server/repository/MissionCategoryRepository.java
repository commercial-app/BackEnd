package com.example.server.repository;

import com.example.server.entity.MissionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MissionCategoryRepository extends JpaRepository<MissionCategory, Long> {
    Optional<MissionCategory> findByName(String name);
}