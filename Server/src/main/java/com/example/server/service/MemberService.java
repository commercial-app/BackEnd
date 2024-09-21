package com.example.server.service;

import com.example.server.common.exception.CustomException;
import com.example.server.common.exception.ErrorCode;
import com.example.server.dto.MemberDTO;
import com.example.server.entity.Member;
import com.example.server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberDTO getMemberDTOByMemberId(Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER))
            .toDTO();
    }

    public Long saveMember(MemberDTO memberDTO) {
        Member member = memberDTO.toEntity();
        return memberRepository.save(member).getMemberId();
    }

    public void existEmail(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }
    }

    public void matchPassword(MemberDTO memberDTO, String password) {
        if (memberDTO.password().equals(password)) {
            throw new CustomException(ErrorCode.PASSWORD_NOT_MATCH);
        }
    }

}
