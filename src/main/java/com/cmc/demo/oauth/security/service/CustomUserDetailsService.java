package com.cmc.demo.oauth.security.service;

import com.cmc.demo.oauth.model.entity.Users;
import com.cmc.demo.oauth.security.entity.MyUserDetails;
import com.cmc.demo.oauth.security.repo.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {
      Users users = userRepository.findUsersByUserName(username);

      if(users == null){
        throw new Exception("Error");
      }

      return new MyUserDetails(users);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // Get user authorities
//  private Set<SimpleGrantedAuthority> getAuthority(Users user) {
//    Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//    user.getRoleSet().forEach(role -> {
//      authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
//    });
//    return authorities;
//  }
}