package com.cmc.demo.oauth.service.impl;

import com.cmc.demo.oauth.exception.TokenRefreshException;
import com.cmc.demo.oauth.model.entity.Permissions;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
                        authenticationRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.createTokenByAuthentication(authentication);

        Users users = userRepository.findUsersByUserName(authentication.getName());

        List<Permissions> permissionsList = permissionRepository.getPermissionsByRoleSet(users.getRoleSet());
        List<String> permisionList = new ArrayList<>();
        for (Permissions permissions : permissionsList) {
            permisionList.add(permissions.getPermissionName());
        }

        List<String> resourceList = new ArrayList<>();
        for (ResourceAssign ra : users.getResourceAssignSet()) {
            resourceList.add(ra.getResourceId());
        }

        AuthenticationResponse response = new AuthenticationResponse();
        response.setUserName(authentication.getName());
        response.setAccessToken(jwt);
        response.setTokenType("Bearer");
        response.setPermissionList(permisionList);
        response.setResourceList(resourceList);
        response.setRefreshToken(users.getRefreshToken() == null ? users.getRefreshToken() : getRefreshTokenNew(users));

        return response;
    }

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        Users users = userRepository.findUsersByUserName(refreshTokenRequest.getUserName());
        if (users.getRefreshTokenExpiryDate().compareTo(Instant.now()) < 0) {
            users.setRefreshToken(null);
            users.setRefreshTokenExpiryDate(null);
            userRepository.saveAndFlush(users);
            throw new TokenRefreshException("Refresh token was expired. Please make a new login request");
        }

        String jwtToken = jwtTokenProvider.createTokenByUserName(users.getUserName());
        RefreshTokenResponse response = new RefreshTokenResponse();
        response.setAccessToken(jwtToken);

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
