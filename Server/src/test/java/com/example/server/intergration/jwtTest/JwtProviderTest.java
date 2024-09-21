package com.example.server.intergration.jwtTest;


import com.example.server.common.jwtUtil.JwtProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JwtProviderTest {

    @Autowired
    private JwtProvider jwtProvider;

    @Test
    void generateJwtTest() {
        Long TEST_ID = 1L;
        String token = jwtProvider.generateToken(TEST_ID);
        Long expected = jwtProvider.getMemberIdFromToken(token);

        assertThat(expected).isEqualTo(TEST_ID);
    }

}
