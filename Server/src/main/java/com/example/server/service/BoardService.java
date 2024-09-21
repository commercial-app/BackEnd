package com.example.server.service;

import com.example.server.common.exception.CustomException;
import com.example.server.common.exception.ErrorCode;
import com.example.server.dto.BoardDTO;
import com.example.server.entity.Board;
import com.example.server.entity.Member;
import com.example.server.entity.Mission;
import com.example.server.entity.MissionSummit;
import com.example.server.entity.MissionSummitState;
import com.example.server.entity.Tile;
import com.example.server.entity.TileState;
import com.example.server.repository.BoardRepository;
import com.example.server.repository.MemberRepository;
import com.example.server.repository.MissionRepository;
import com.example.server.repository.MissionSummitRepository;
import com.example.server.repository.TileRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final TileRepository tileRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final MissionSummitRepository missionSummitRepository;

    /**
     * 유저 회원가입 시 보드를 생성하고, 20개의 타일과 미션을 할당한 후 반환
     */
    public BoardDTO createBoard(Long memberId) {
        // 유저(Member) 정보 조회
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

        // 보드 생성
        Board board = new Board();
        board.setMember(member);
        board.setMemberPosition(0); // 초기 멤버 위치는 0으로 설정

        // 20개의 타일 생성 및 미션 할당
        List<Tile> tiles = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Mission mission = getRandomMission(); // 무작위 미션 선택
            Tile tile = new Tile();
            tile.setBoard(board);
            tile.setMission(mission);
            tile.setOrder(i); // 타일 순서 0부터 19까지
            tile.setState(TileState.OPEN); // 초기 상태는 미션 미완료
            tiles.add(tile);
        }

        // 보드에 타일 설정
        board.setTiles(tiles);

        // 보드와 타일 저장
        boardRepository.save(board);
        tileRepository.saveAll(tiles);

        // 타일 리스트를 DTO로 변환
        List<BoardDTO.TileDTO> tileDTOs = convertTilesToDTOs(tiles,
            board.getMember().getMemberId());

        // 생성된 보드 반환
        return new BoardDTO(board.getId(), board.getMemberPosition(), tileDTOs);
    }

    /**
     * 보드 상태 조회
     */
    public BoardDTO getBoard(Long boardId) {
        // 보드 정보 조회
        Board board = boardRepository.findById(boardId)
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_BOARD));

        // 타일 리스트를 DTO로 변환
        List<BoardDTO.TileDTO> tileDTOs = convertTilesToDTOs(board.getTiles(),
            board.getMember().getMemberId());

        // 보드 DTO 반환
        return new BoardDTO(board.getId(), board.getMemberPosition(), tileDTOs);
    }

    /**
     * 무작위 미션 선택 메서드
     */
    private Mission getRandomMission() {
        List<Mission> missions = missionRepository.findAll();
        return missions.get(new Random().nextInt(missions.size()));
    }

    /**
     * 주사위의 눈금만큼 업데이트 만약, isCycle 상태라면 완료한 미션 교체, tile state 초기화 후 반환
     */
    public BoardDTO moveMember(Long boardId, Integer dice, boolean isCycle) {
        // 보드 정보 조회
        Board board = boardRepository.findById(boardId)
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_BOARD));

        // 주사위를 굴려 멤버 위치 이동
        board.moveMemberPosition(dice);

        // 한 바퀴 돌면 미션 갱신
        if (isCycle) {
            updateMissionsOnBoard(board);
        }

        // 타일 리스트를 DTO로 변환
        List<BoardDTO.TileDTO> tileDTOs = convertTilesToDTOs(board.getTiles(),
            board.getMember().getMemberId());

        // 업데이트된 보드 상태 반환
        return new BoardDTO(board.getId(), board.getMemberPosition(), tileDTOs);
    }

    /**
     * 한 바퀴 돌았을 때 완료한 미션을 새로운 미션으로 갱신
     */
    private void updateMissionsOnBoard(Board board) {
        // 보드에 속한 모든 타일 가져오기
        List<Tile> tiles = tileRepository.findByBoard(board);

        // 완료한 타일의 미션을 새로운 미션으로 변경
        for (Tile tile : tiles) {
            if (tile.getState().equals(TileState.CLOSE)) { // 완료한 미션인 경우에만 미션 변경
                Mission newMission = getRandomMission();
                tile.changeMission(newMission);
                tile.changeState(TileState.OPEN); // 미션 갱신 후 완료 상태 초기화
                tileRepository.save(tile);  // 개별 타일 저장
            }
        }
    }

    /**
     * 타일들을 DTO로 변환
     */
    private List<BoardDTO.TileDTO> convertTilesToDTOs(List<Tile> tiles, Long memberId) {
        return tiles.stream().map(tile -> {
            // 미션 제출 상태 조회 (가장 최근의 MissionSummit 조회)
            Optional<MissionSummit> summitOpt = missionSummitRepository
                .findTopByMemberIdAndMissionIdOrderByCreatedAtDesc(memberId,
                    tile.getMission().getMissionId());

            // 미션 제출 상태 및 반려 사유 설정
            MissionSummitState missionSummitState = summitOpt.map(MissionSummit::getState)
                .orElse(null);
            String rejectionReason = summitOpt.map(MissionSummit::getRejection).orElse(null);

            // 카테고리 이름
            String categoryName = tile.getMission().getMissionCategory().getName();

            return new BoardDTO.TileDTO(
                tile.getId(),
                tile.getOrder(),
                tile.getState(),
                new BoardDTO.MissionDTO(
                    tile.getMission().getMissionId(),
                    tile.getMission().getTitle(),
                    tile.getMission().getContent(),
                    tile.getMission().getImageUrl(),
                    categoryName,        // 카테고리 이름
                    missionSummitState,  // 제출 상태
                    rejectionReason      // 반려 사유
                )
            );
        }).collect(Collectors.toList());
    }
}