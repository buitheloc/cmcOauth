package com.cmc.demo.oauth.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequest {
    private Long roleId;
    private String roleName;
    private String slug;
    private String description;
}
