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
import com.example.server.repository.MemberRepository;
import com.example.server.repository.MissionRepository;
import com.example.server.repository.MissionSummitRepository;
import com.example.server.repository.TileRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MissionSummitService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MissionSummitRepository missionSummitRepository;
    private final TileRepository tileRepository;

    private static final String UPLOAD_DIR = "uploads/";

    @Transactional
    public Long saveMissionSummit(MissionSummitRequest request, Long memberId, Long missionId) {
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));
        Mission mission = missionRepository.findById(missionId)
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MISSION));

        String imageUrl = saveImage(request.image());

        MissionSummit summit = MissionSummit.builder()
            .member(member)
            .mission(mission)
            .imageUrl(imageUrl)
            .content(request.content())
            .state(MissionSummitState.PENDING)
            .build();

        MissionSummit savedSummit = missionSummitRepository.save(summit);
        return savedSummit.getId();
    }

    private String saveImage(MultipartFile file) {
        try {
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path uploadPath = Paths.get(UPLOAD_DIR);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath);

            return UPLOAD_DIR + fileName;
        } catch (IOException e) {
            throw new CustomException(ErrorCode.FILE_UPLOAD_ERROR);
        }
    }

    @Transactional
    public void updateSummitResult(Long summitId, SummitResultRequest request) {
        MissionSummit missionSummit = missionSummitRepository.findById(summitId)
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MISSION_SUMMIT));
        missionSummit.setState(request.state());
        if (request.state().equals(MissionSummitState.REJECTED)) {
            missionSummit.setContent(request.comment());
            //보드 서비스 호출(타일위의 미션dto에 상태와 코멘트 갱신)
            return;
        }
        Member member = missionSummit.getMember();
        Mission mission = missionSummit.getMission();
        member.addCompletedMission(new CompletedMission(0L, member, mission));
        Tile tile = findTimeByMissionIdAndMemberId(mission.getMissionId(), member.getMemberId());
        tile.changeState(TileState.CLOSE);
        memberRepository.save(member);
        tileRepository.save(tile);
    }

    @Transactional(readOnly = true)
    public List<MissionSummitDTO> getMissionSummitList() {
        return missionSummitRepository.findAllByState(MissionSummitState.PENDING).stream()
            .map(MissionSummit::toDTO).collect(
                Collectors.toList());
    }

    public Tile findTimeByMissionIdAndMemberId(Long missionId, Long memberId) {
        return tileRepository.findByMissionIdAndMemberId(missionId, memberId)
            .get();
    }

}