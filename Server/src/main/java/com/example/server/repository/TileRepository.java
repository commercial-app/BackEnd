package com.example.server.repository;

import com.example.server.entity.Board;
import com.example.server.entity.Tile;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TileRepository extends JpaRepository<Tile, Long> {
    List<Tile> findByBoard(Board board); // 보드 내 타일들을 찾기 위한 메서드

    @Query("SELECT t FROM Tile t " +
        "JOIN t.board b " +
        "JOIN b.member m " +
        "WHERE t.mission.missionId = :missionId " +
        "AND m.memberId = :memberId")
    Optional<Tile> findByMissionIdAndMemberId(@Param("missionId") Long missionId,
        @Param("memberId") Long memberId);

}
