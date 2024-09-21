package com.example.server.dto;

import com.example.server.entity.Role;

public record MemberDTO(Long memberId, String email, String name, Long point, Role role) {

}
