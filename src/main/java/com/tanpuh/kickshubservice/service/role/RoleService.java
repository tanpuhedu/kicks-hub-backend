package com.tanpuh.kickshubservice.service.role;

import com.tanpuh.kickshubservice.dto.request.RoleCreationRequest;
import com.tanpuh.kickshubservice.dto.request.RoleUpdateRequest;
import com.tanpuh.kickshubservice.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {
    List<RoleResponse> getAll();
    RoleResponse create(RoleCreationRequest request);
    RoleResponse update(Integer id, RoleUpdateRequest request);
    void delete(Integer id);
}
