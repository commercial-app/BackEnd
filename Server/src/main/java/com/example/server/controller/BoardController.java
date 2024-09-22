package com.example.server.controller;

import com.example.server.common.annotation.LoginMembers;
import com.example.server.dto.BoardDTO;
import com.example.server.dto.MemberDTO;
import com.example.server.dto.requestDTO.MissionSummitRequest;
import com.example.server.service.BoardService;
import com.example.server.service.MissionSummitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final MissionSummitService missionSummitService;

    /**
     * 보드 정보 조회
     *
     * @return BoardDTO
     */
    @GetMapping
    public ResponseEntity<BoardDTO> getBoard(@LoginMembers MemberDTO memberDTO) {
        BoardDTO boardDTO = boardService.getBoard(memberDTO.memberId());
        return ResponseEntity.ok(boardDTO);
    }

    /**
     * 주사위를 굴려 멤버의 위치를 업데이트하고, 필요시 미션 갱신
     *
     * @param boardId 보드 ID
     * @param dice    주사위 눈금
     * @param isCycle 한 바퀴를 돌았는지 여부
     * @return 업데이트된 BoardDTO
     */
    @PostMapping("/{boardId}/move")
    public ResponseEntity<BoardDTO> moveMember(@PathVariable Long boardId,
        @RequestParam Integer dice,
        @RequestParam boolean isCycle) {
        BoardDTO updatedBoard = boardService.moveMember(boardId, dice, isCycle);
        return ResponseEntity.ok(updatedBoard);
    }

    @PostMapping("/{mission_id}")
    public ResponseEntity<?> requestMissionSummit(@PathVariable("mission_id") Long mission_id,
        @LoginMembers MemberDTO memberDTO,
        @RequestBody MissionSummitRequest request) {
        missionSummitService.saveMissionSummit(request, memberDTO.memberId(), mission_id);
        return ResponseEntity.ok(mission_id);
    }
}