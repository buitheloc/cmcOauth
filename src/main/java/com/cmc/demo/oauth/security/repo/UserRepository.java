package com.cmc.demo.oauth.security.repo;

import com.cmc.demo.oauth.security.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
  @Query("Select u from Users u where u.userName = :userName")
  Optional<Users> findByUserName(@Param("userName") String userName);
}
