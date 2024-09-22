package com.example.server.controller;

import com.example.server.dto.CreateMissionDTO;
import com.example.server.dto.MissionSummitDTO;
import com.example.server.dto.requestDTO.SummitResultRequest;
import com.example.server.entity.MissionCategory;
import com.example.server.entity.MissionSummitState;
import com.example.server.service.MissionService;
import com.example.server.service.MissionSummitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final MissionSummitService missionSummitService;
    private final MissionService missionService;

    /**
     * 미션 제출 목록 페이지
     */
    @GetMapping("/missions")
    public String getMissionSummitList(Model model) {
        List<MissionSummitDTO> missionSummits = missionSummitService.getMissionSummitList();
        model.addAttribute("missionSummits", missionSummits);
        return "admin/mission-list";  // Thymeleaf 템플릿 파일 경로
    }

    /**
     * 미션 제출 상세 페이지
     */
    @GetMapping("/missions/{summitId}")
    public String getMissionSummitDetail(@PathVariable Long summitId, Model model) {
        MissionSummitDTO missionSummit = missionSummitService.getMissionSummitById(summitId);
        model.addAttribute("missionSummit", missionSummit);
        return "admin/mission-detail";  // 상세 페이지 템플릿
    }

    /**
     * 미션 제출 결과 업데이트 후 목록으로 리다이렉트
     */
    @PostMapping("/missions/{summitId}/update")
    public String updateMissionSummit(@PathVariable Long summitId,
                                      @RequestParam MissionSummitState state,
                                      @RequestParam(required = false) String rejection) {
        SummitResultRequest request = new SummitResultRequest(state, rejection);
        missionSummitService.updateSummitResult(summitId, request);
        return "redirect:/admin/missions";  // 성공 후 목록 페이지로 리다이렉트
    }

    /**
     * 미션 생성 페이지
     */
    @GetMapping("/missions/create")
    public String showCreateMissionPage(Model model) {
        // 빈 CreateMissionDTO 객체를 생성하여 모델에 추가
        model.addAttribute("createMissionDTO", new CreateMissionDTO());
        return "admin/create-mission";  // 미션 생성 페이지 템플릿
    }

    /**
     * 미션 생성 처리
     */
    @PostMapping("/missions/create")
    public String createMission(@ModelAttribute CreateMissionDTO createMissionDTO, @RequestParam String categoryName) {
        MissionCategory category = new MissionCategory();  // 카테고리를 새로 만들거나 찾는 로직 필요
        category.setName(createMissionDTO.getCategoryName());
        missionService.createMission(createMissionDTO, category);
        return "redirect:/admin/missions";  // 성공 후 목록 페이지로 리다이렉트
    }

    /**
     * 모든 미션 조회 페이지
     */
    @GetMapping("/missions/all")
    public String getAllMissions(Model model) {
        List<CreateMissionDTO> missions = missionService.getAllMissions();
        model.addAttribute("missions", missions);
        return "admin/mission-list-all";  // 모든 미션 조회 템플릿
    }
}