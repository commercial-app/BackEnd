package com.example.server.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.server.dto.BoardDTO;
import com.example.server.entity.*;
import com.example.server.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;

public class BoardServiceTest {

    @InjectMocks
    private BoardService boardService;


    @Mock
    private BoardRepository boardRepository;

    @Mock
    private TileRepository tileRepository;

    @Mock
    private MissionRepository missionRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private MissionSummitRepository missionSummitRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // 임의의 MissionCategory 생성 메서드
    private MissionCategory createDummyMissionCategory() {
        MissionCategory category = new MissionCategory();
        category.setName("Test Category");
        return category;
    }

    // 임의의 Mission 생성 메서드
    private Mission createDummyMission() {
        Mission mission = new Mission();
        mission.setMissionId(1L);
        mission.setTitle("Test Mission");
        mission.setContent("Test Content");
        mission.setImageUrl("http://testimage.com");

        // MissionCategory 설정
        mission.setMissionCategory(createDummyMissionCategory());

        return mission;
    }

    // 임의의 Tile 생성 메서드
    private Tile createDummyTile(Board board, int order) {
        Tile tile = new Tile();
        tile.setBoard(board);
        tile.setMission(createDummyMission());
        tile.setOrder(order);
        tile.setState(TileState.OPEN);
        return tile;
    }

    // 임의의 Member 생성 메서드
    private Member createDummyMember() {
        Member member = new Member();
        member.setMemberId(1L);
        member.setName("Test User");
        return member;
    }

    @Test
    public void testGetBoard_Success() {
        // 임의의 Board 생성
        Board board = new Board();
        board.setMember(createDummyMember());
        board.setMemberPosition(0);

        Tile tile = createDummyTile(board, 0); // 타일 생성
        board.setTiles(List.of(tile));

        // Mock 동작 정의
        when(boardRepository.findById(anyLong())).thenReturn(Optional.of(board));

        // 보드 상태 조회 테스트 실행
        BoardDTO boardDTO = boardService.getBoard(1L);

        // 검증
        assertNotNull(boardDTO);
        assertEquals(0, boardDTO.getMemberPosition());
        assertEquals(1, boardDTO.getTiles().size());
        assertEquals("Test Mission", boardDTO.getTiles().get(0).getMission().getTitle());
        assertEquals("Test Category", boardDTO.getTiles().get(0).getMission().getCategoryName());
    }

    @Test
    public void testMoveMember_Success() {
        // 임의의 Board 생성
        Board board = new Board();
        board.setMember(createDummyMember());
        board.setMemberPosition(0);

        Tile tile = createDummyTile(board, 0); // 타일 생성
        board.setTiles(List.of(tile));

        // Mock 동작 정의
        when(boardRepository.findById(anyLong())).thenReturn(Optional.of(board));

        // 주사위 굴리기 및 멤버 이동 테스트 실행
        BoardDTO updatedBoard = boardService.moveMember(1L, 3, false);

        // 검증
        assertNotNull(updatedBoard);
        assertEquals(3, updatedBoard.getMemberPosition());
        assertEquals(1, updatedBoard.getTiles().size());
        assertEquals("Test Mission", updatedBoard.getTiles().get(0).getMission().getTitle());
        assertEquals("Test Category", updatedBoard.getTiles().get(0).getMission().getCategoryName());
    }

//    @Test
//    public void testUpdateMissionsOnBoard_Success() {
//        // 임의의 Board 생성
//        Board board = new Board();
//        board.setMember(createDummyMember());
//        board.setMemberPosition(0);  // 명시적으로 초기 위치 설정
//
//        Tile tile = createDummyTile(board, 0);
//        tile.setState(true); // 완료된 미션으로 설정
//        board.setTiles(List.of(tile));
//
//        // Mock 동작 정의
//        when(boardRepository.findById(anyLong())).thenReturn(Optional.of(board));
//        when(missionRepository.findAll()).thenReturn(List.of(createDummyMission()));
//
//        // 한 바퀴 돌았을 때 미션 업데이트 테스트 실행
//        boardService.moveMember(1L, 0, true); // isCycle true로 설정하여 미션 교체
//
//        // 미션 갱신 후 검증
//        verify(tileRepository, times(1)).save(any(Tile.class));
//    }
}
