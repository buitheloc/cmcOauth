package com.cmc.demo.oauth.controller;

import com.cmc.demo.oauth.mapper.UserInfoMapper;
import com.cmc.demo.oauth.security.dto.response.UserResponse;
import com.cmc.demo.oauth.security.repo.UserRepository;
import com.cmc.demo.oauth.service.PermissionAccess;
import com.cmc.demo.oauth.util.BaseResponse;
import com.cmc.demo.oauth.util.ResponseFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin
public class UserControllerDemo {

    final
    UserRepository userRepository;
    final
    UserInfoMapper userInfoMapper;

    public UserControllerDemo(UserRepository userRepository, UserInfoMapper userInfoMapper) {
        this.userRepository = userRepository;
        this.userInfoMapper = userInfoMapper;
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<BaseResponse<UserResponse>> getUserById(@PathVariable Long id) {
        UserResponse res = userInfoMapper.toDomain(userRepository.getReferenceById(id));

        return ResponseFactory.success(HttpStatus.OK, res);
    }

}
