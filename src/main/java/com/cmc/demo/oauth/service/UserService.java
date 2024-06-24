package com.cmc.demo.oauth.service;

import com.cmc.demo.oauth.dto.request.UserRequest;
import com.cmc.demo.oauth.dto.response.UserResponse;

public interface UserService {
    UserResponse generateUserLogin(String userName) throws Exception;
    UserResponse createUser(UserRequest userRequest);
    UserResponse updateUser(UserRequest userRequest);
    UserResponse changePassword(UserRequest userRequest);
    UserResponse resetPassword(UserRequest userRequest);
}
