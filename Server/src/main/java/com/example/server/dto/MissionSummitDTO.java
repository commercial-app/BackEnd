package com.example.server.dto;


import com.example.server.entity.MissionSummit;
import com.example.server.entity.MissionSummitState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MissionSummitDTO {
    private Long id;
    private String imageUrl;
    private String content;
    private MissionSummitState state;
    private String rejection;
    private Long missionId;
    private Long memberId;

    // 생성자 오버로딩: ID를 제외한 생성자 (새로운 MissionSummit 생성 시 사용)
    public MissionSummitDTO(String imageUrl, String content, MissionSummitState state,
        String rejection, Long missionId, Long memberId) {
        this.imageUrl = imageUrl;
        this.content = content;
        this.state = state;
        this.rejection = rejection;
        this.missionId = missionId;
        this.memberId = memberId;
    }

    // 엔티티로 변환하는 메서드 (필요시 사용)
    public MissionSummit toEntity() {
        MissionSummit missionSummit = new MissionSummit();
        missionSummit.setId(this.id);
        missionSummit.setImageUrl(this.imageUrl);
        missionSummit.setContent(this.content);
        missionSummit.setState(this.state);
        missionSummit.setRejection(this.rejection);
        return missionSummit;
    }
}