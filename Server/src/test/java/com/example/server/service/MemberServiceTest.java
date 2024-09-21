package com.example.server.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.server.dto.MemberDTO;
import com.example.server.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    private final String TEST_NAME = "TEST_NAME";
    private final String TEST_EMAIL = "TEST_EMAIL";
    private final String TEST_PASSWORD = "TEST_PASSWORD";
    private final Long TEST_POINT = 0L;

    @Test
    void testGetMemberDTOByMemberId() throws NotFoundException {
        MemberDTO memberDTO = new MemberDTO(1L, TEST_EMAIL, TEST_PASSWORD, TEST_NAME, TEST_POINT,
            Role.USER);
        long memberId = memberService.saveMember(memberDTO);
        MemberDTO result = memberService.getMemberDTOByMemberId(memberId);

        assertThat(result).isEqualTo(memberDTO);
    }
}
