package com.cmc.demo.oauth.controller;

import com.cmc.demo.oauth.model.entity.Users;
import com.cmc.demo.oauth.security.repo.UserRepository;
import com.cmc.demo.oauth.util.BaseResponse;
import com.cmc.demo.oauth.util.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin
public class UserControllerDemo {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<Users>> getUserById(@PathVariable Long id) {
        return ResponseFactory.success(HttpStatus.OK, userRepository.getReferenceById(id));
    }

}
