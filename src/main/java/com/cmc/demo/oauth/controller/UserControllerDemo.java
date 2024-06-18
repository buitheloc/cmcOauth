package com.cmc.demo.oauth.controller;

import com.cmc.demo.oauth.security.dto.response.UserResponse;
import com.cmc.demo.oauth.mapper.UserInfoMapper;
import com.cmc.demo.oauth.security.repo.UserRepository;
import com.cmc.demo.oauth.service.PermissionAccess;
import com.cmc.demo.oauth.util.BaseResponse;
import com.cmc.demo.oauth.util.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin
public class UserControllerDemo {

    final
    UserRepository userRepository;

    public UserControllerDemo(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    UserInfoMapper userInfoMapper;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN1')")
    public ResponseEntity<BaseResponse<UserResponse>> getUserById(@PathVariable Long id) {

//        if(!PermissionAccess.havePermissionAccess("USER")){
//            return ResponseFactory.success(HttpStatus.OK, null, "Not Access");
//        }

        UserResponse res = userInfoMapper.toDomain(userRepository.getReferenceById(id));

        return ResponseFactory.success(HttpStatus.OK, res);
    }

}
