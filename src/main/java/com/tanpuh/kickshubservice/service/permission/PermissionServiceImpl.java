package com.tanpuh.kickshubservice.service.permission;

import com.tanpuh.kickshubservice.dto.request.PermissionRequest;
import com.tanpuh.kickshubservice.dto.response.PermissionResponse;
import com.tanpuh.kickshubservice.entity.Permission;
import com.tanpuh.kickshubservice.exception.AppException;
import com.tanpuh.kickshubservice.exception.ErrorCode;
import com.tanpuh.kickshubservice.mapper.PermissionMapper;
import com.tanpuh.kickshubservice.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionServiceImpl implements PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper mapper;

    @Override
    public List<PermissionResponse> getAll() {
        return permissionRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public PermissionResponse create(PermissionRequest request) {
        Permission permission = mapper.toEntity(request);
        return mapper.toResponse(permissionRepository.save(permission));
    }

    @Override
    public PermissionResponse update(Integer id, PermissionRequest request) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PERMISSION_NOT_FOUND));

        mapper.update(permission, request);

        return mapper.toResponse(permissionRepository.save(permission));
    }

    @Override
    public void delete(Integer id) {
        permissionRepository.deleteById(id);
    }
}
