package com.cmc.demo.oauth.service.impl;

import com.cmc.demo.oauth.exception.TokenRefreshException;
import com.cmc.demo.oauth.model.entity.ResourceAssign;
import com.cmc.demo.oauth.model.entity.Users;
import com.cmc.demo.oauth.repository.PermissionRepository;
import com.cmc.demo.oauth.repository.UserRepository;
import com.cmc.demo.oauth.security.dto.request.AuthenticationRequest;
import com.cmc.demo.oauth.security.dto.request.RefreshTokenRequest;
import com.cmc.demo.oauth.security.dto.response.AuthenticationResponse;
import com.cmc.demo.oauth.security.dto.response.RefreshTokenResponse;
import com.cmc.demo.oauth.security.utils.JwtTokenProvider;
import com.cmc.demo.oauth.service.AuthService;
import com.cmc.demo.oauth.util.MD5;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;

    @Value("${jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;


    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository, PermissionRepository permissionRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public AuthenticationResponse authenticateUser(AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                        MD5.convertPassword(authenticationRequest.getPassword()))
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.createTokenByAuthentication(authentication);

        Users users = userRepository.findUsersByUserName(authentication.getName());
        List<String> permisionList = new ArrayList<>();
        for (Object obj : authentication.getAuthorities()) {
            permisionList.add(obj.toString());
        }

        List<String> resourceList = users.getResourceAssignSet().stream().map(ResourceAssign::getResourceId).collect(Collectors.toList());

        AuthenticationResponse response = new AuthenticationResponse();
        response.setUserName(authentication.getName());
        response.setAccessToken(jwt);
        response.setTokenType("Bearer");
        response.setPermissionList(permisionList);
        response.setResourceList(resourceList);
        response.setRefreshToken(users.getRefreshToken() == null ? getRefreshTokenNew(users) : users.getRefreshToken());

        return response;
    }

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        Users users = userRepository.findUsersByUserName(refreshTokenRequest.getUsername());
        if (users.getRefreshTokenExpiryDate().compareTo(Instant.now()) < 0) {
            users.setRefreshToken(null);
            users.setRefreshTokenExpiryDate(null);
            userRepository.saveAndFlush(users);
            throw new TokenRefreshException("Refresh token was expired. Please make a new login request");
        }

        String jwtToken = jwtTokenProvider.createTokenByUserName(users.getUserName());
        RefreshTokenResponse response = new RefreshTokenResponse();
        response.setAccessToken(jwtToken);
        response.setRefreshToken(refreshTokenRequest.getRefreshToken());

        return response;
    }

    public String getRefreshTokenNew(Users users) {
        String refreshToken = UUID.randomUUID().toString();
        users.setRefreshToken(refreshToken);
        users.setRefreshTokenExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        userRepository.saveAndFlush(users);

        return refreshToken;
    }
}
