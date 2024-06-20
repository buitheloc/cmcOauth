package com.cmc.demo.oauth.repository;

import com.cmc.demo.oauth.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    @Query("Select u From Users u Where u.userName = :userName  order by u.lastModifiedDate desc limit 1")
    Users findUsersByUserName(@Param("userName") String userName);
}
