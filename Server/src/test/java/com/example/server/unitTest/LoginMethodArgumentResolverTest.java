package com.example.server.unitTest;

import com.example.server.common.annotation.LoginMembers;
import com.example.server.common.jwtUtil.JwtProvider;
import com.example.server.common.resolver.LoginMethodArgumentResolver;
import com.example.server.dto.MemberDTO;
import com.example.server.entity.Role;
import com.example.server.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoginMethodArgumentResolverTest {

    @Mock
    private JwtProvider jwtProvider;

    @Mock
    private MemberService memberService;

    @Mock
    private MethodParameter methodParameter;

    @Mock
    private NativeWebRequest webRequest;

    private LoginMethodArgumentResolver resolver;

    @BeforeEach
    void setUp() {
        resolver = new LoginMethodArgumentResolver(jwtProvider, memberService);
    }

    @Test
    void supportsParameter_withLoginMembersAnnotation_returnsTrue() {
        when(methodParameter.hasParameterAnnotation(LoginMembers.class)).thenReturn(true);

        assertTrue(resolver.supportsParameter(methodParameter));
    }

    @Test
    void supportsParameter_withoutLoginMembersAnnotation_returnsFalse() {
        when(methodParameter.hasParameterAnnotation(LoginMembers.class)).thenReturn(false);

        assertFalse(resolver.supportsParameter(methodParameter));
    }

    @Test
    void resolveArgument_withValidToken_returnsMemberDTO() throws Exception {
        String token = "valid.jwt.token";
        Long memberId = 1L;
        MemberDTO expectedMemberDTO = new MemberDTO(memberId, "test@example.com", "password", "Test User", 100L, Role.USER);

        when(webRequest.getHeader("authorization")).thenReturn("Bearer " + token);
        when(jwtProvider.getMemberIdFromToken(token)).thenReturn(memberId);
        when(memberService.getMemberDTOByMemberId(memberId)).thenReturn(expectedMemberDTO);

        Object result = resolver.resolveArgument(methodParameter, null, webRequest, null);

        assertNotNull(result);
        assertTrue(result instanceof MemberDTO);
        assertEquals(expectedMemberDTO, result);
    }

    @Test
    void resolveArgument_withInvalidToken_returnsNull() throws Exception {
        when(webRequest.getHeader("authorization")).thenReturn("InvalidToken");

        Object result = resolver.resolveArgument(methodParameter, null, webRequest, null);

        assertNull(result);
    }

    @Test
    void resolveArgument_withNullAuthorization_returnsNull() throws Exception {
        when(webRequest.getHeader("authorization")).thenReturn(null);

        Object result = resolver.resolveArgument(methodParameter, null, webRequest, null);

        assertNull(result);
    }

    @Test
    void getToken_withValidBearerToken_returnsToken() {
        String token = "valid.jwt.token";
        String authHeader = "Bearer " + token;

        String result = resolver.getToken(authHeader);

        assertEquals(token, result);
    }

    @Test
    void getToken_withInvalidBearerToken_returnsNull() {
        String authHeader = "InvalidToken";

        String result = resolver.getToken(authHeader);

        assertNull(result);
    }

    @Test
    void getToken_withNullAuthHeader_returnsNull() {
        String result = resolver.getToken(null);

        assertNull(result);
    }
}