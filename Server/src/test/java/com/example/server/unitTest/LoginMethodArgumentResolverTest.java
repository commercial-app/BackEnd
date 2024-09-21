package com.example.server.unitTest;

import com.example.server.common.annotation.LoginMembers;
import com.example.server.common.exception.CustomException;
import com.example.server.common.exception.ErrorCode;
import com.example.server.common.exception.GlobalExceptionHandler;
import com.example.server.common.jwtUtil.JwtProvider;
import com.example.server.common.resolver.LoginMethodArgumentResolver;
import com.example.server.dto.MemberDTO;
import com.example.server.dto.responseDTO.ErrorResponse;
import com.example.server.entity.Role;
import com.example.server.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginMethodArgumentResolverTest {

    @InjectMocks
    private LoginMethodArgumentResolver resolver;

    @Mock
    private JwtProvider jwtProvider;

    @Mock
    private MemberService memberService;

    @Mock
    private NativeWebRequest webRequest;

    @Mock
    private MethodParameter methodParameter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void supportsParameter_withLoginMembersAnnotation_returnsTrue() {
        when(methodParameter.hasParameterAnnotation(LoginMembers.class)).thenReturn(true);
        assertTrue(resolver.supportsParameter(methodParameter));
    }

    @Test
    void resolveArgument_validToken_returnsMemberDTO() throws Exception {
        String token = "valid.jwt.token";
        Long memberId = 1L;
        MemberDTO memberDTO = new MemberDTO(memberId, "test@test.com", "password", "Test User", 100L, Role.USER);

        when(webRequest.getHeader("authorization")).thenReturn("Bearer " + token);
        when(jwtProvider.getMemberIdFromToken(token)).thenReturn(memberId);
        when(memberService.getMemberDTOByMemberId(memberId)).thenReturn(memberDTO);

        Object result = resolver.resolveArgument(methodParameter, null, webRequest, null);

        assertNotNull(result);
        assertTrue(result instanceof MemberDTO);
        assertEquals(memberDTO, result);
    }

    @Test
    void resolveArgument_noToken_throwsCustomException() {
        when(webRequest.getHeader("authorization")).thenReturn(null);

        CustomException exception = assertThrows(CustomException.class,
            () -> resolver.resolveArgument(methodParameter, null, webRequest, null));

        assertEquals(ErrorCode.INVALID_TOKEN, exception.getErrorCode());
    }

    @Test
    void getToken_validAuthorizationHeader_returnsToken() {
        String token = "valid.jwt.token";
        String authHeader = "Bearer " + token;

        String result = resolver.getToken(authHeader);

        assertEquals(token, result);
    }

    @Test
    void getToken_invalidAuthorizationHeader_returnsNull() {
        String authHeader = "InvalidHeader";

        String result = resolver.getToken(authHeader);

        assertNull(result);
    }
}

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void handleCustomException_invalidToken_returnsCorrectResponseEntity() {
        CustomException exception = new CustomException(ErrorCode.INVALID_TOKEN);
        ResponseEntity<ErrorResponse> responseEntity = exceptionHandler.handleCustomException(exception);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(ErrorCode.INVALID_TOKEN.getState().value(), responseEntity.getBody().code());
        assertEquals(ErrorCode.INVALID_TOKEN.getMessage(), responseEntity.getBody().message());
    }

    @Test
    void handleCustomException_tokenExpired_returnsCorrectResponseEntity() {
        CustomException exception = new CustomException(ErrorCode.TOKEN_EXPIRED);
        ResponseEntity<ErrorResponse> responseEntity = exceptionHandler.handleCustomException(exception);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(ErrorCode.TOKEN_EXPIRED.getState().value(), responseEntity.getBody().code());
        assertEquals(ErrorCode.TOKEN_EXPIRED.getMessage(), responseEntity.getBody().message());
    }

    @Test
    void handleCustomException_notFoundMember_returnsCorrectResponseEntity() {
        CustomException exception = new CustomException(ErrorCode.NOT_FOUND_MEMBER);
        ResponseEntity<ErrorResponse> responseEntity = exceptionHandler.handleCustomException(exception);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(ErrorCode.NOT_FOUND_MEMBER.getState().value(), responseEntity.getBody().code());
        assertEquals(ErrorCode.NOT_FOUND_MEMBER.getMessage(), responseEntity.getBody().message());
    }
}