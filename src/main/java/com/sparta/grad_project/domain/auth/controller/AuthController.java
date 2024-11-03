package com.sparta.grad_project.domain.auth.controller;

import com.sparta.grad_project.domain.auth.dto.request.SigninRequestDto;
import com.sparta.grad_project.domain.auth.dto.request.SignupRequestDto;
import com.sparta.grad_project.domain.auth.service.AuthService;
import com.sparta.grad_project.global.config.ApiResponse;
import com.sparta.grad_project.global.exception.dto.ErrorResponse;
import com.sparta.grad_project.global.jwt.JwtUtil;
import jakarta.security.auth.message.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import com.sparta.grad_project.domain.user.enums.UserRole;
import jakarta.servlet.http.Cookie;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtUtil jwtUtil;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    // 회원가입
    @PostMapping("/auth/signup")
    public ResponseEntity<ApiResponse<String>> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        return ResponseEntity.ok(ApiResponse.success(authService.signup(signupRequestDto)));
    }

    // 로그인
//    @PostMapping("/auth/signin")
//    public ResponseEntity<ApiResponse<String>> signin(@Valid @RequestBody SigninRequestDto signinRequestDto)
//            throws AuthException, IOException {
//        logger.info("뭔데");
//        String message = authService.signin(signinRequestDto);
//        logger.info(message);
//        return ResponseEntity.ok(ApiResponse.success(message));
//    }


    @PostMapping("/auth/logout")
    public ResponseEntity<ApiResponse<String>> logout(HttpServletResponse response) {
        logger.info("로갓");
        // 리프레시 토큰 쿠키 삭제
        Cookie cookie1 = new Cookie("Access-Token", null);
        cookie1.setPath("/"); // 쿠키의 경로 설정
        cookie1.setMaxAge(0); // 쿠키 만료 설정
        response.addCookie(cookie1);
        // 리프레시 토큰 쿠키 삭제
        Cookie cookie2 = new Cookie("Refresh-Token", null);
        cookie2.setPath("/"); // 쿠키의 경로 설정
        cookie2.setMaxAge(0); // 쿠키 만료 설정
        response.addCookie(cookie2);

        return ResponseEntity.ok(ApiResponse.success("로그아웃 성공"));
    }

    @GetMapping("/auth/check")
    public ResponseEntity<ApiResponse<String>> checkAuthStatus(HttpServletRequest request, HttpServletResponse response) {
        String accessToken = jwtUtil.getAccessTokenFromCookie(request);

        // AccessToken이 존재하는 경우
        if (accessToken != null) {
            if (jwtUtil.validateToken(accessToken)) {
                return ResponseEntity.ok(ApiResponse.success("isLoggedIn"));
            }

            // AccessToken이 만료된 경우 RefreshToken으로 새 AccessToken 발급
            String refreshToken = String.valueOf(jwtUtil.getRefreshTokenFromCookie(request));
            if (refreshToken != null && jwtUtil.validateRefreshToken(refreshToken)) {
                String newAccessToken = jwtUtil.getAccessTokenFromCookie(request); // 새로운 AccessToken 생성
                jwtUtil.setAccessTokenCookie(response, newAccessToken);
                return ResponseEntity.ok(ApiResponse.success("isLoggedIn"));
            }
        }

        // 로그인 상태가 아닌 경우
        return ResponseEntity.ok(ApiResponse.success("notLoggedIn"));
    }

    @GetMapping("/auth/csrf")
    public CsrfToken csrf(CsrfToken token) {
        return token; // CSRF 토큰 반환
    }

}
