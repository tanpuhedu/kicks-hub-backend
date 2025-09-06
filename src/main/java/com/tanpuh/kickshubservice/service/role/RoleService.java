package com.tanpuh.kickshubservice.service.role;

import com.tanpuh.kickshubservice.dto.request.RoleRequest;
import com.tanpuh.kickshubservice.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {
    List<RoleResponse> getAll();
    RoleResponse create(RoleRequest request);
    RoleResponse update(Integer id, RoleRequest request);
    void delete(Integer id);
}
