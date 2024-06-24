package com.cmc.demo.oauth.mapper.request;

import com.cmc.demo.oauth.dto.request.UserRequest;
import com.cmc.demo.oauth.mapper.LcMapper;
import com.cmc.demo.oauth.model.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRequestMapper extends LcMapper<Users, UserRequest> {
}
