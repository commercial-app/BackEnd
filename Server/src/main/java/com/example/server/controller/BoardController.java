package com.example.server.controller;

import com.example.server.dto.BoardDTO;
import com.example.server.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /**
     * 보드 정보 조회
     *
     * @param boardId 보드 ID
     * @return BoardDTO
     */
    @GetMapping("/{boardId}")
    public ResponseEntity<BoardDTO> getBoard(@PathVariable Long boardId) {
        BoardDTO boardDTO = boardService.getBoard(boardId);
        return ResponseEntity.ok(boardDTO);
    }

    /**
     * 주사위를 굴려 멤버의 위치를 업데이트하고, 필요시 미션 갱신
     *
     * @param boardId 보드 ID
     * @param dice 주사위 눈금
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
}