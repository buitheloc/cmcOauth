package com.cmc.demo.oauth.mapper;

import com.cmc.demo.oauth.model.entity.Users;
import com.cmc.demo.oauth.security.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserInfoMapper extends LcMapper<Users, UserResponse>{
}
