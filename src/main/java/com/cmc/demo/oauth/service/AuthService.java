package com.cmc.demo.oauth.service;

import com.cmc.demo.oauth.security.dto.request.AuthenticationRequest;
import com.cmc.demo.oauth.security.dto.request.RefreshTokenRequest;
import com.cmc.demo.oauth.security.dto.response.AuthenticationResponse;
import com.cmc.demo.oauth.security.dto.response.RefreshTokenResponse;

public interface AuthService {
    AuthenticationResponse authenticateUser(AuthenticationRequest authenticationRequest);
    RefreshTokenResponse refreshToken(RefreshTokenRequest authenticationRequest);
}
