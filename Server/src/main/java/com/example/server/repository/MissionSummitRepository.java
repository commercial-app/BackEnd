package com.example.server.repository;

import com.example.server.entity.MissionSummit;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionSummitRepository extends JpaRepository<MissionSummit, Long> {

    @Query("SELECT ms FROM MissionSummit ms WHERE ms.member.memberId = :memberId AND ms.mission.missionId = :missionId")
    Optional<MissionSummit> findByMemberIdAndMissionId(@Param("memberId") Long memberId,
        @Param("missionId") Long missionId);

}
