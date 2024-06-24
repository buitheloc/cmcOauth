package com.cmc.demo.oauth.repository;

import com.cmc.demo.oauth.model.entity.Role;
import com.cmc.demo.oauth.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
