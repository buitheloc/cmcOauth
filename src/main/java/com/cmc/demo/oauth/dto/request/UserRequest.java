package com.cmc.demo.oauth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String userName;
    private String email;
    private String password;
    private boolean isActive;
    private String description;
    private List<String> roleList;
    private List<Long> roleIdList;
    private List<String> permissionList;
    private List<Long> permissionIdList;
    private List<String> resourceId;
    private List<Long> resourceAssignId;
}
