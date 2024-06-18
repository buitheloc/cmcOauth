package com.cmc.demo.oauth.security.service;

import com.cmc.demo.oauth.model.entity.Users;
import com.cmc.demo.oauth.security.entity.MyUserDetails;
import com.cmc.demo.oauth.security.repo.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
}