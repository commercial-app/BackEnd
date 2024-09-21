package com.example.server.repository;

import com.example.server.entity.MissionSummit;
import com.example.server.entity.MissionSummitState;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionSummitRepository extends JpaRepository<MissionSummit, Long> {

    @Query("SELECT ms FROM MissionSummit ms WHERE ms.member.memberId = :memberId AND ms.mission.missionId = :missionId ORDER BY ms.created_at DESC")
    Optional<MissionSummit> findTopByMemberIdAndMissionIdOrderByCreatedAtDesc(@Param("memberId") Long memberId,
        @Param("missionId") Long missionId);

    List<MissionSummit> findAllByState(MissionSummitState state);

}
