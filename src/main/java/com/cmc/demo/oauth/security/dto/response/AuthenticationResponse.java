package com.cmc.demo.oauth.security.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String userName;
    private String accessToken;
    private String tokenType;
    private String refreshToken;
    private List<String> permissionList;
    private List<String> resourceList;
}
