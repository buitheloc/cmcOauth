package com.cmc.demo.oauth.service.impl;

import com.cmc.demo.oauth.dto.request.UserRequest;
import com.cmc.demo.oauth.dto.response.UserResponse;
import com.cmc.demo.oauth.mapper.request.UserRequestMapper;
import com.cmc.demo.oauth.model.entity.ResourceAssign;
import com.cmc.demo.oauth.model.entity.Role;
import com.cmc.demo.oauth.model.entity.Users;
import com.cmc.demo.oauth.repository.ResourceAssignRepository;
import com.cmc.demo.oauth.repository.RoleRepository;
import com.cmc.demo.oauth.repository.UserRepository;
import com.cmc.demo.oauth.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    final
    UserRepository userRepository;

    final
    UserRequestMapper userRequestMapper;

    final
    RoleRepository roleRepository;

    final
    ResourceAssignRepository resourceAssignRepository;

    public UserServiceImpl(UserRepository userRepository, UserRequestMapper userRequestMapper, RoleRepository roleRepository, ResourceAssignRepository resourceAssignRepository) {
        this.userRepository = userRepository;
        this.userRequestMapper = userRequestMapper;
        this.roleRepository = roleRepository;
        this.resourceAssignRepository = resourceAssignRepository;
    }

    @Override
    public UserResponse generateUserLogin(String userName) throws Exception {
        Users users = userRepository.getUserStartWith(userName);
        UserResponse response = new UserResponse();
        if (users == null) {
            response.setUserName(userName);
        } else {
            response.setUserName(generateUserName(userName));
        }

        return response;
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        if(userRequest == null){
            return null;
        }

        Users users = userRequestMapper.toEntity(userRequest);
        users.setActive(userRequest.isActive());

        if (userRequest.getRoleIdList() != null && !userRequest.getRoleIdList().isEmpty()){
            Set<Role> roleSet = (Set<Role>) roleRepository.findAllById(userRequest.getRoleIdList());
            users.setRoleSet(roleSet);
        }

        if(userRequest.getResourceId() != null && !userRequest.getResourceId().isEmpty()){
            Set<ResourceAssign> resourceAssignSet = (Set<ResourceAssign>) resourceAssignRepository.findAllById(userRequest.getResourceAssignId());
            users.setResourceAssignSet(resourceAssignSet);
        }

        userRepository.save(users);

        return null;
    }

    @Override
    public UserResponse updateUser(UserRequest userRequest) {


        Users users = userRepository.findUsersByUserName(userRequest.getUserName());

        if (users == null){
            return null;
        }


        return null;
    }

    @Override
    public UserResponse changePassword(UserRequest userRequest) {
        return null;
    }

    @Override
    public UserResponse resetPassword(UserRequest userRequest) {
        return null;
    }

    private String generateUserName(String userName) throws Exception {
        int index = 0;
        for (int i = userName.length() - 1; i >= 0; i--) {
            if (Character.isDigit(userName.charAt(i))) {
                continue;
            }
            index = i + 1;
            break;
        }
        String endWithCount = userName.substring(index);
        if (StringUtils.isEmpty(endWithCount)) {
            return userName + "1";
        }
        Pattern pattern = Pattern.compile("\\d*");
        if (!pattern.matcher(endWithCount).matches()) {
            throw new Exception();
        }

        return userName.substring(0, index) + (Integer.parseInt(userName.substring(index)) + 1);
    }
}
