package com.tanpuh.kickshubservice.mapper;

import com.tanpuh.kickshubservice.dto.request.PermissionRequest;
import com.tanpuh.kickshubservice.dto.response.PermissionResponse;
import com.tanpuh.kickshubservice.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toEntity(PermissionRequest dto);
    PermissionResponse toResponse(Permission entity);
    void update(@MappingTarget Permission entity, PermissionRequest dto);
}
