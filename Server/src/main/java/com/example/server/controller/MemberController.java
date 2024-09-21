package com.example.server.controller;

import com.example.server.common.annotation.LoginMembers;
import com.example.server.common.jwtUtil.JwtProvider;
import com.example.server.dto.MemberDTO;
import com.example.server.dto.requestDTO.LoginRequest;
import com.example.server.dto.requestDTO.RegisterRequest;
import com.example.server.dto.responseDTO.JwtResponse;
import com.example.server.entity.Role;
import com.example.server.service.BoardService;
import com.example.server.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final BoardService boardService;
    private final JwtProvider jwtProvider;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        memberService.existEmail(request.email());
        MemberDTO memberDTO = new MemberDTO(0L, request.email(), request.password(), request.name(),
            0L, Role.USER);
        Long createdId = memberService.saveMember(memberDTO);
        boardService.createBoard(createdId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdId);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {
        MemberDTO memberDTO = memberService.getMemberDTOByEmail(request.email());
        memberService.matchPassword(memberDTO, request.password());
        String token = jwtProvider.generateToken(memberDTO.memberId());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @GetMapping("/member")
    public ResponseEntity<MemberDTO> getMember(@LoginMembers MemberDTO memberDTO) {
        return ResponseEntity.ok(memberDTO);
    }

}
