package com.cmc.demo.oauth.dto.response;

import com.cmc.demo.oauth.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String userId;
    private String userName;
    private String email;
    private String description;
    private Set<Role> roleSet;
}
