package com.cmc.demo.oauth.security.service;

import com.cmc.demo.oauth.model.entity.Users;
import com.cmc.demo.oauth.security.dto.response.UserResponse;
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
      Users users = userRepository.findUsersByUserName(username);

      if(users == null){
        throw new Exception("Error");
      }

      UserResponse userResponse = new UserResponse();
      userResponse.setUserName(users.getUserName());
      userResponse.setPassword(users.getPassword());


      return userResponse;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}