package com.cmc.demo.oauth.controller;

import com.cmc.demo.oauth.dto.request.UserRequest;
import com.cmc.demo.oauth.dto.response.UserResponse;
import com.cmc.demo.oauth.mapper.response.UserResponseMapper;
import com.cmc.demo.oauth.repository.UserRepository;
import com.cmc.demo.oauth.service.UserService;
import com.cmc.demo.oauth.util.BaseResponse;
import com.cmc.demo.oauth.util.ResponseFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    private final UserRepository userRepository;
    private final UserResponseMapper userResponseMapper;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserResponseMapper userResponseMapper, UserService userService) {
        this.userRepository = userRepository;
        this.userResponseMapper = userResponseMapper;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    //@RolesAllowed({"ADMIN_EDIT"})
    public ResponseEntity<BaseResponse<UserResponse>> getUserById(@PathVariable Long id) {
        UserResponse res = userResponseMapper.toDomain(userRepository.getReferenceById(id));
        return ResponseFactory.success(HttpStatus.OK, res);
    }


    @GetMapping("/generate_user_login")
    //@RolesAllowed({"ADMIN_CREATE_USER"})
    public ResponseEntity<BaseResponse<UserResponse>> generateUserLogin(
            @PathVariable String userName) throws Exception {
        return ResponseFactory.success(userService.generateUserLogin(userName));
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse<String>> createUser(@RequestBody UserRequest userRequest){
        userService.createUser(userRequest);
        return ResponseFactory.success(HttpStatus.OK, "Success", "Create user success");
    }
}
