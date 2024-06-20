package com.cmc.demo.oauth.repository;

import com.cmc.demo.oauth.model.entity.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permissions, Long> {
    @Query("select p from Permissions p where p.permissionId in (select rp.permissions.permissionId from RolePermissions rp where rp.role.roleId in :roleSet)")
    List<Permissions> getPermissionsByRoleSet(@Param("roleSet") List<Long> roleSet);
}
