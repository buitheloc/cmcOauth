package com.cmc.demo.oauth.controller;

import com.cmc.demo.oauth.security.dto.request.AuthenticationRequest;
import com.cmc.demo.oauth.security.dto.request.RefreshTokenRequest;
import com.cmc.demo.oauth.security.dto.response.AuthenticationResponse;
import com.cmc.demo.oauth.security.dto.response.RefreshTokenResponse;
import com.cmc.demo.oauth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticateUser(
            @RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authService.authenticateUser(authenticationRequest));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<RefreshTokenResponse> refreshToken(
            @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logout successful");
    }
}