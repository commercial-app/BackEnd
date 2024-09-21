package com.example.server.repository;

import com.example.server.entity.Board;
import com.example.server.entity.Tile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TileRepository extends JpaRepository<Tile, Long> {
    List<Tile> findByBoard(Board board); // 보드 내 타일들을 찾기 위한 메서드
}
