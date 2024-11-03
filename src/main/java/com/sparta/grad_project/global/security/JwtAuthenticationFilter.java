package com.sparta.grad_project.global.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.grad_project.domain.auth.dto.request.SigninRequestDto;
import com.sparta.grad_project.domain.user.entity.User;
import com.sparta.grad_project.global.jwt.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collections;

@Slf4j(topic = "로그인 및 JWT 생성")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/auth/signin");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            SigninRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), SigninRequestDto.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getEmail(),
                            requestDto.getPassword(),
                            null
                    )
            );
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        User user = userDetails.getUser();

        // AccessToken과 RefreshToken 생성
        String accessToken = jwtUtil.createAccessToken(user.getEmail(), user.getRole());
        String refreshToken = jwtUtil.createRefreshToken(user.getEmail(), user.getRole());

        // 쿠키에 AccessToken 및 RefreshToken 설정
        response.addCookie(createCookie("Access-Token", accessToken));
        response.addCookie(createCookie("Refresh-Token", refreshToken));

        // JSON 응답 추가
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK); // 상태 코드 200 설정

        // JSON 응답 작성
        try {
            String jsonResponse = new ObjectMapper().writeValueAsString(Collections.singletonMap("message", "로그인 성공"));
            response.getWriter().write(jsonResponse);
            response.getWriter().flush(); // 버퍼를 비우고 응답을 전송
        } catch (JsonProcessingException e) {
            log.error("JSON 변환 오류: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 상태 코드 500 설정
            response.getWriter().write("{\"message\":\"Internal Server Error\"}");
        }
    }

    private Cookie createCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // HTTPS에서만 사용 가능
        cookie.setPath("/"); // 모든 경로에서 유효
        return cookie;
    }




    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        log.error("Authentication failed: {}", failed.getMessage());
        response.setStatus(401);
    }
}
