package com.example.server.service;

import com.example.server.dto.BoardDTO;
import com.example.server.entity.Board;
import com.example.server.entity.Mission;
import com.example.server.entity.MissionCategory;
import com.example.server.entity.Tile;
import com.example.server.repository.BoardRepository;
import com.example.server.repository.MissionRepository;
import com.example.server.repository.TileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final TileRepository tileRepository;
    private final MissionRepository missionRepository;

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

    /**
     * 멤버 위치 이동
     */
    public BoardDTO moveMember(Long boardId, Integer dice, boolean isCycle) {
        // 보드 정보 조회
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid board id"));

        // 주사위를 굴려 멤버 위치 이동
        board.moveMemberPosition(dice);

        // 한 바퀴 돌면 미션 갱신
        if (isCycle) {
//            updateMissionsOnBoard(board);
            // 보드판에 이미 완료한 미션 초기화
        }

        // 타일 리스트를 DTO로 변환
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

        // 업데이트된 보드 상태 반환
        return new BoardDTO(board.getId(), board.getMemberPosition(), tileDTOs);
    }

//    /**
//     * 한 바퀴 돌았을 때 새로운 미션으로 갱신
//     */
//    private void updateMissionsOnBoard(Board board) {
//        // 보드에 속한 모든 타일 가져오기
//        List<Tile> tiles = tileRepository.findByBoard(board);
//
//        // 각 타일의 미션을 새로운 미션으로 변경
//        for (Tile tile : tiles) {
//            Mission newMission = getRandomMission(tile.getMission().getMissionCategory());
//            tile.changeMission(newMission);
//            tile.changeState(false); // 미션 갱신 후 완료 상태 초기화
//        }
//    }
//
//    /**
//     * 랜덤 미션 선택
//     */
//    private Mission getRandomMission(MissionCategory category) {
//        List<Mission> missions = missionRepository.findAllByMissionCategory(category);
//        return missions.get(new Random().nextInt(missions.size()));
//    }
//
//
}
