package com.example.server.dto;

import com.example.server.entity.MissionSummitState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMissionDTO {
    private Long missionId;  // 미션 ID 추가
    private String title;              // 미션의 제목
    private String content;            // 미션의 설명 내용
    private String imageUrl;           // 미션과 관련된 이미지 URL
    private String categoryName;       // 미션 카테고리
}
