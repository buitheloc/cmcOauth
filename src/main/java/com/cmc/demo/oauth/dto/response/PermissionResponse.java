package com.cmc.demo.oauth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PermissionResponse {
    private Long permissionId;
    private String permissionName;
    private String description;
}
