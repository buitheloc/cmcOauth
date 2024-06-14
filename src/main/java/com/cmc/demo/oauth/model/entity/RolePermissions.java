package com.cmc.demo.oauth.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "role_permissions")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class RolePermissions extends Audit<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_permissions_id")
    private Long rolePermissionsId;

    @JoinColumn(name = "permission_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Permissions permissions;

    @JoinColumn(name = "role_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Role role;
}
