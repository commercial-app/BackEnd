package com.example.server.service;

import com.example.server.dto.BoardDTO;
import com.example.server.entity.Board;
import com.example.server.repository.BoardRepository;
import com.example.server.repository.TileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final TileRepository tileRepository;

    public BoardDTO getBoard(Long boardId) {
        // board 정보를 조회
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid board id"));

        // 각 타일 정보를 DTO로 변환
        List<BoardDTO.TileDTO> tileDTOs = board.getTiles().stream().map(tile ->
                new BoardDTO.TileDTO(
                        tile.getId(),
                        tile.getOrder(),
                        tile.getState(),
                        new BoardDTO.MissionDTO(
                                tile.getMission().getMissionId(),
                                tile.getMission().getTitle(),
                                tile.getMission().getContent(),
                                tile.getMission().getImageUrl(),
                                tile.getMission().getMissionCategory().getName()
                        )
                )
        ).collect(Collectors.toList());

        // 최종적으로 BoardDTO 반환
        return new BoardDTO(board.getId(), board.getMemberPosition(), tileDTOs);
    }
}
