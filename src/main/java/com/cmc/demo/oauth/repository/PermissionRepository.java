package com.cmc.demo.oauth.repository;

import com.cmc.demo.oauth.model.entity.Permissions;
import com.cmc.demo.oauth.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PermissionRepository extends JpaRepository<Permissions, Long> {
    @Query("Select p From Permissions p Where p.rolePermissionsSet in " +
            "(select rs from RolePermissions rs where rs.role in :roleSet)")
    List<Permissions> getPermissionsByRoleSet(Set<Role> roleSet);
}
