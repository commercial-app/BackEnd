package com.example.server.service;

import com.example.server.dto.MemberDTO;
import com.example.server.entity.Member;
import com.example.server.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberDTO getMemberDTOByMemberId(Long memberId) throws NotFoundException {
        return memberRepository.findById(memberId).orElseThrow(NotFoundException::new)
            .toDTO();
    }

    public Long saveMember(MemberDTO memberDTO) {
        Member member = memberDTO.toEntity();
        return memberRepository.save(member).getMemberId();
    }

}
