package com.cmc.demo.oauth.security.service;

import com.cmc.demo.oauth.model.entity.Permissions;
import com.cmc.demo.oauth.model.entity.Role;
import com.cmc.demo.oauth.model.entity.Users;
import com.cmc.demo.oauth.repository.PermissionRepository;
import com.cmc.demo.oauth.repository.UserRepository;
import com.cmc.demo.oauth.security.entity.MyUserDetails;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;
  private final PermissionRepository permissionRepository;

  public CustomUserDetailsService(UserRepository userRepository, PermissionRepository permissionRepository) {
    this.userRepository = userRepository;
    this.permissionRepository = permissionRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {
      Users users = userRepository.findUsersByUserName(username);

      List<Long> roleIdList = users.getRoleSet().stream().map(Role::getRoleId).collect(Collectors.toList());
      List<Permissions> permissionsList = permissionRepository.getPermissionsByRoleSet(roleIdList);
      List<String> permisionList = permissionsList.stream().map(Permissions::getPermissionName).collect(Collectors.toList());

      if (users == null) {
        throw new Exception("Error");
      }

      return new MyUserDetails(users, permisionList);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}