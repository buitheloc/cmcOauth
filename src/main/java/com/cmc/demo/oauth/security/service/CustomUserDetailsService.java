package com.cmc.demo.oauth.security.service;

import com.cmc.demo.oauth.security.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {
      return (UserDetails) userRepository.findByUserName(username)
          .orElseThrow(() -> new Exception("user Not found "));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}