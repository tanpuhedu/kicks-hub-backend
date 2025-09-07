package com.tanpuh.kickshubservice.mapper;

import com.tanpuh.kickshubservice.dto.request.RoleCreationRequest;
import com.tanpuh.kickshubservice.dto.response.RoleResponse;
import com.tanpuh.kickshubservice.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = PermissionMapper.class)
public interface RoleMapper {
    Role toEntity(RoleCreationRequest dto);
    RoleResponse toResponse(Role entity);
}
