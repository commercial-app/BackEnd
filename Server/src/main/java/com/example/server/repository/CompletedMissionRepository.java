package com.example.server.repository;

import com.example.server.entity.CompletedMission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompletedMissionRepository extends JpaRepository<CompletedMission, Long> {

}
