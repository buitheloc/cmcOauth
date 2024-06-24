package com.cmc.demo.oauth.repository;

import com.cmc.demo.oauth.model.entity.Permissions;
import com.cmc.demo.oauth.model.entity.ResourceAssign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceAssignRepository extends JpaRepository<ResourceAssign, Long> {
}
