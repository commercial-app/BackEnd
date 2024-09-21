package com.example.server.dto;

import com.example.server.entity.Member;
import com.example.server.entity.Role;
import java.util.ArrayList;

public record MemberDTO(Long memberId, String email, String password, String name, Long point,
                        Role role) {

    public Member toEntity() {
        return new Member(memberId, email, password, name, point, role, new ArrayList<>());
    }
}
