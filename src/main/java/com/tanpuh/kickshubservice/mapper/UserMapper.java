package com.tanpuh.kickshubservice.mapper;

import com.tanpuh.kickshubservice.dto.request.UserCreationRequest;
import com.tanpuh.kickshubservice.dto.request.UserUpdateRequest;
import com.tanpuh.kickshubservice.dto.response.UserResponse;
import com.tanpuh.kickshubservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "phone", ignore = true)
    User toEntity(UserCreationRequest dto);

    UserResponse toResponse(User entity);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "phone", ignore = true)
    void update(@MappingTarget User entity, UserUpdateRequest dto);
}
