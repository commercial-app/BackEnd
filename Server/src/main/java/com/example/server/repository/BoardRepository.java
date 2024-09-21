package com.example.server.repository;

import com.example.server.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findByMember_MemberId(Long memberId);
}
