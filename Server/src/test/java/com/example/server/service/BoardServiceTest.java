package com.example.server.service;
import com.example.server.dto.BoardDTO;
import com.example.server.entity.*;
import com.example.server.repository.BoardRepository;
import com.example.server.repository.MemberRepository;
import com.example.server.repository.MissionRepository;
import com.example.server.repository.TileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BoardServiceTest {

    @Mock
    private BoardRepository boardRepository;

    @Mock
    private TileRepository tileRepository;

    @Mock
    private MissionRepository missionRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private BoardService boardService;

    private Member member;
    private List<Mission> missions;
    private Board board;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // 테스트용 가짜 데이터 생성
        member = new Member();
        member.setMemberId(1L);
        member.setEmail("test@example.com");
        member.setName("Test User");

        // MissionCategory 생성
        MissionCategory category = new MissionCategory();
        category.setCategoryId(1L);
        category.setName("Test Category");

        // 미션 리스트 생성
        missions = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Mission mission = new Mission();
            mission.setMissionId((long) i);
            mission.setTitle("Mission " + i);
            mission.setContent("Content for mission " + i);
            mission.setMissionCategory(category);
            missions.add(mission);
        }

        // 보드 생성
        board = new Board();
        board.setId(1L);
        board.setMember(member);
        board.setMemberPosition(0);
        List<Tile> tiles = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Tile tile = new Tile();
            tile.setId((long) i + 1);
            tile.setOrder(i);
            tile.setState(false);
            tile.setMission(missions.get(new Random().nextInt(missions.size())));
            tiles.add(tile);
        }
        board.setTiles(tiles);
    }

    @Test
    @DisplayName("유저 이동")
    void testMoveMember() {
        // 가짜 데이터 및 Mock 설정
        when(boardRepository.findById(1L)).thenReturn(Optional.of(board));
        when(tileRepository.findByBoard(board)).thenReturn(board.getTiles());

        // 테스트 실행 (주사위 값 3, isCycle = false)
        BoardDTO boardDTO = boardService.moveMember(1L, 3, false);

        // 검증
        assertEquals(1L, boardDTO.getBoardId());
        assertEquals(3, boardDTO.getMemberPosition());
        verify(boardRepository, times(1)).findById(1L);
        verify(tileRepository, times(0)).saveAll(anyList());  // isCycle=false 이므로 미션 갱신이 일어나지 않아야 함
    }

    @Test
    @DisplayName("isCycle")
    void testMoveMemberWithCycle() {
        // 가짜 데이터 및 Mock 설정
        when(boardRepository.findById(1L)).thenReturn(Optional.of(board));
        when(tileRepository.findByBoard(board)).thenReturn(board.getTiles());
        when(missionRepository.findAll()).thenReturn(missions);

        // 테스트 실행 (주사위 값 3, isCycle = true)
        BoardDTO boardDTO = boardService.moveMember(1L, 3, true);

        // 검증
        assertEquals(1L, boardDTO.getBoardId());
        assertEquals(3, boardDTO.getMemberPosition());
        verify(boardRepository, times(1)).findById(1L);
        verify(tileRepository, times(1)).saveAll(anyList());  // isCycle=true 이므로 미션 갱신이 일어나야 함
    }
}