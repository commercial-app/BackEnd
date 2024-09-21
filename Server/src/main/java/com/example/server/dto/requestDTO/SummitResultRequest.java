package com.example.server.dto.requestDTO;

import com.example.server.entity.MissionSummitState;

public record SummitResultRequest(MissionSummitState state, String comment) {

}
