package com.tanpuh.kickshubservice.service.permission;

import com.tanpuh.kickshubservice.dto.request.PermissionRequest;
import com.tanpuh.kickshubservice.dto.response.PermissionResponse;

import java.util.List;

public interface PermissionService {
    List<PermissionResponse> getAll();
    PermissionResponse create(PermissionRequest request);
    PermissionResponse update(Integer id, PermissionRequest request);
    void delete(Integer id);
}
