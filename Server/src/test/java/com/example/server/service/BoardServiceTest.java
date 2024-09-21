package com.example.server.service;

import com.example.server.dto.BoardDTO;
import com.example.server.entity.Board;
import com.example.server.entity.Mission;
import com.example.server.entity.MissionCategory;
import com.example.server.entity.Tile;
import com.example.server.repository.BoardRepository;
import com.example.server.repository.TileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class BoardServiceTest {

    @Mock
    private BoardRepository boardRepository;

    @Mock
    private TileRepository tileRepository;

    @InjectMocks
    private BoardService boardService;

    private Board board;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // 테스트용 가짜 데이터 생성
        MissionCategory category = new MissionCategory();
        category.setName("Category 1");

        Mission mission = new Mission();
        mission.setMissionId(1L);
        mission.setTitle("Mission 1");
        mission.setContent("Content 1");
        mission.setImageUrl("url1");
        mission.setMissionCategory(category);

        Tile tile = new Tile(1L, null, mission, 1, true);
        List<Tile> tiles = new ArrayList<>();
        tiles.add(tile);

        board = new Board();
        board.setId(1L);
        board.setMemberPosition(5);
        board.setTiles(tiles);
    }

    @Test
    void getBoard_success() {
        // 가짜 데이터로 리포지토리 동작을 정의
        when(boardRepository.findById(1L)).thenReturn(Optional.of(board));

        // 서비스 로직 호출
        BoardDTO boardDTO = boardService.getBoard(1L);

        // 검증
        assertEquals(1L, boardDTO.getBoardId());
        assertEquals(5, boardDTO.getMemberPosition());
        assertEquals(1L, boardDTO.getTiles().get(0).getTileId());
        assertEquals("Mission 1", boardDTO.getTiles().get(0).getMission().getTitle());
    }
}
