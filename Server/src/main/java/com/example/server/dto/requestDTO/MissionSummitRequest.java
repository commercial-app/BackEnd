package com.example.server.dto.requestDTO;

import org.springframework.web.multipart.MultipartFile;

public record MissionSummitRequest(MultipartFile image, String content) {

}