package com.example.server.service;

import com.example.server.dto.CreateMissionDTO;
import com.example.server.entity.Mission;
import com.example.server.entity.MissionCategory;
import com.example.server.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    /**
     * 미션 생성 메서드
     */
    @Transactional
    public Long createMission(CreateMissionDTO createMissionDTO, MissionCategory category) {
        Mission mission = new Mission();
        mission.setTitle(createMissionDTO.getTitle());
        mission.setContent(createMissionDTO.getContent());
        mission.setImageUrl(createMissionDTO.getImageUrl());
        mission.setMissionCategory(category);

        Mission savedMission = missionRepository.save(mission);
        return savedMission.getMissionId();
    }

    /**
     * 모든 미션 조회 메서드
     */
    @Transactional(readOnly = true)
    public List<CreateMissionDTO> getAllMissions() {
        return missionRepository.findAll().stream()
                .map(mission -> new CreateMissionDTO(
                        mission.getTitle(),
                        mission.getContent(),
                        mission.getImageUrl(),
                        mission.getMissionCategory().getName()
                )).collect(Collectors.toList());
    }
}