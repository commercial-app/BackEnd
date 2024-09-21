package com.example.server.common.resolver;

import com.example.server.common.annotation.LoginMembers;
import com.example.server.common.jwtUtil.JwtProvider;
import com.example.server.dto.MemberDTO;
import com.example.server.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
public class LoginMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtProvider jwtProvider;
    private final MemberService memberService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginMembers.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String token = getToken(webRequest.getHeader("authorization"));
        Long memberId = jwtProvider.getMemberIdFromToken(token);
        MemberDTO memberDTO = memberService.getMemberDTOByMemberId(memberId);
        return memberDTO;
    }

    public String getToken(String author) {
        if (author != null && author.startsWith("Bearer ")) {
            return author.substring(7);
        }
        return null;
    }
}
