package com.example.server.dto;

import com.example.server.entity.MissionSummitState;
import com.example.server.entity.TileState;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardDTO {

    private Long boardId;           // 보드의 고유 ID
    private Integer memberPosition; // 보드 위의 멤버가 위치한 현재 타일의 위치
    private List<TileDTO> tiles;    // 해당 보드에 포함된 타일들의 리스트

    @Data
    @AllArgsConstructor
    public static class TileDTO {

        private Long tileId;          // 타일의 고유 ID
        private Integer order;        // 타일의 순서(보드에서의 위치)
        private TileState state;        // 해당 타일의 상태(미션 완료 여부)
        private MissionDTO mission;   // 타일에 연결된 미션 정보
    }

    @Data
    @AllArgsConstructor
    public static class MissionDTO {

        private Long missionId;      // 미션의 고유 ID
        private String title;        // 미션의 제목
        private String content;      // 미션의 설명 내용
        private String imageUrl;     // 미션과 관련된 이미지 URL
        private String categoryName; // 미션이 속한 카테고리 이름
        private MissionSummitState missionSummitState; // 미션 제출 여부
        private String rejection; // 미션 거절 멘트
    }
}
