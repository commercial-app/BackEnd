package com.example.server.service;

import com.example.server.common.exception.CustomException;
import com.example.server.common.exception.ErrorCode;
import com.example.server.dto.MissionSummitDTO;
import com.example.server.dto.requestDTO.MissionSummitRequest;
import com.example.server.dto.requestDTO.SummitResultRequest;
import com.example.server.entity.CompletedMission;
import com.example.server.entity.Member;
import com.example.server.entity.Mission;
import com.example.server.entity.MissionSummit;
import com.example.server.entity.MissionSummitState;
import com.example.server.entity.Tile;
import com.example.server.entity.TileState;
import com.example.server.repository.CompletedMissionRepository;
import com.example.server.repository.MemberRepository;
import com.example.server.repository.MissionRepository;
import com.example.server.repository.MissionSummitRepository;
import com.example.server.repository.TileRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MissionSummitService {

    // 의존성 주입: Member, Mission, MissionSummit, Tile 관련 레포지토리들
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MissionSummitRepository missionSummitRepository;
    private final TileRepository tileRepository;
    private final CompletedMissionRepository completedMissionRepository;

    @Transactional
    public Long saveMissionSummit(MissionSummitRequest request, Long memberId, Long missionId) {
        // memberId로 회원 정보 조회. 없으면 예외 발생
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

        // missionId로 미션 정보 조회. 없으면 예외 발생
        Mission mission = missionRepository.findById(missionId)
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MISSION));

        // 이미지를 서버에 저장하고 경로를 받아옴
        String imageUrl = request.imageUrl();

        // 미션 제출 객체 생성 후 저장
        MissionSummit summit = MissionSummit.builder()
            .member(member)
            .mission(mission)
            .imageUrl(imageUrl)
            .content(request.content())  // 제출된 미션에 대한 설명
            .state(MissionSummitState.PENDING)  // 초기 상태는 PENDING
            .build();

        // 미션 제출 정보를 DB에 저장하고 ID를 반환
        MissionSummit savedSummit = missionSummitRepository.save(summit);
        return savedSummit.getId();
    }


    @Transactional
    public void updateSummitResult(Long summitId, SummitResultRequest request) {
        // summitId로 미션 제출 정보 조회
        MissionSummit missionSummit = missionSummitRepository.findById(summitId)
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MISSION_SUMMIT));

        // 미션 제출 상태 업데이트 (APPROVED 또는 REJECTED)
        missionSummit.setState(request.state());

        // 제출이 반려된 경우 코멘트를 저장하고 반환
        if (request.state().equals(MissionSummitState.REJECTED)) {
            missionSummit.setContent(request.comment());
            return;  // 반려된 경우 별도의 처리 없이 종료
        }

        // 제출이 승인된 경우 완료된 미션을 회원에게 추가
        Member member = memberRepository.findById(missionSummit.getMember().getMemberId())
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));
        Mission mission = missionSummit.getMission();
        CompletedMission completed = completedMissionRepository.save(
            new CompletedMission(0L, member, mission));
        member.addCompletedMission(completed);

        // 타일 상태 업데이트 (완료 상태로 변경)
        Tile tile = findTimeByMissionIdAndMemberId(mission.getMissionId(), member.getMemberId());
        tile.changeState(TileState.CLOSE);  // 미션 완료

    }

    @Transactional(readOnly = true)
    public List<MissionSummitDTO> getMissionSummitList() {
        // 상태가 PENDING인 미션 제출 목록을 조회하고 DTO로 변환
        return missionSummitRepository.findAllByState(MissionSummitState.PENDING).stream()
            .map(MissionSummit::toDTO)
            .collect(Collectors.toList());
    }

    public Tile findTimeByMissionIdAndMemberId(Long missionId, Long memberId) {
        // 회원과 미션에 해당하는 타일을 조회
        return tileRepository.findByMissionIdAndMemberId(missionId, memberId).get();
    }

    @Transactional(readOnly = true)
    public MissionSummitDTO getMissionSummitById(Long summitId) {
        MissionSummit missionSummit = missionSummitRepository.findById(summitId)
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MISSION_SUMMIT));
        return missionSummit.toDTO();
    }
}