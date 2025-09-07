package com.tanpuh.kickshubservice.service.role;

import com.tanpuh.kickshubservice.dto.request.RoleCreationRequest;
import com.tanpuh.kickshubservice.dto.request.RoleUpdateRequest;
import com.tanpuh.kickshubservice.dto.response.RoleResponse;
import com.tanpuh.kickshubservice.entity.Permission;
import com.tanpuh.kickshubservice.entity.Role;
import com.tanpuh.kickshubservice.exception.AppException;
import com.tanpuh.kickshubservice.exception.ErrorCode;
import com.tanpuh.kickshubservice.mapper.RoleMapper;
import com.tanpuh.kickshubservice.repository.PermissionRepository;
import com.tanpuh.kickshubservice.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleServiceImpl implements RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper mapper;

    @Override
    public List<RoleResponse> getAll() {
        return roleRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public RoleResponse create(RoleCreationRequest request) {
        if (roleRepository.existsByName(request.getName()))
            throw new AppException(ErrorCode.ROLE_EXISTED);

        Role role = mapper.toEntity(request);

        List<Permission> permissions = permissionRepository.findAllById(request.getPermissionIds());
        role.setPermissions(new HashSet<>(permissions));

        return mapper.toResponse(roleRepository.save(role));
    }

    @Override
    public RoleResponse update(Integer id, RoleUpdateRequest request) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));

        var permissions = permissionRepository.findAllById(request.getPermissionIds());
        role.setPermissions(new HashSet<>(permissions));

        return mapper.toResponse(roleRepository.save(role));
    }

    @Override
    public void delete(Integer id) {
        roleRepository.deleteById((id));
    }
}
