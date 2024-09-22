package com.example.server.controller;

import com.example.server.dto.MissionSummitDTO;
import com.example.server.dto.requestDTO.SummitResultRequest;
import com.example.server.entity.MissionSummitState;
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
}