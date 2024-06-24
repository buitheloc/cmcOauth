package com.cmc.demo.oauth.mapper.response;

import com.cmc.demo.oauth.mapper.LcMapper;
import com.cmc.demo.oauth.model.entity.Users;
import com.cmc.demo.oauth.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserResponseMapper extends LcMapper<Users, UserResponse> {
}
