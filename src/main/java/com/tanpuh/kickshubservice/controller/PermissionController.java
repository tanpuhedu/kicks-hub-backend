package com.tanpuh.kickshubservice.controller;

import com.tanpuh.kickshubservice.dto.request.PermissionRequest;
import com.tanpuh.kickshubservice.dto.response.ApiResponse;
import com.tanpuh.kickshubservice.dto.response.PermissionResponse;
import com.tanpuh.kickshubservice.service.permission.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Permission Controller")
@Slf4j
public class PermissionController {
    PermissionService permissionService;

    @GetMapping
    @Operation(summary = "get list of permissions")
    public ApiResponse<List<PermissionResponse>> getAll() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .data(permissionService.getAll())
                .message("Get list of permissions successfully")
                .build();
    }

    @PostMapping
    @Operation(summary = "create new permission")
    public ApiResponse<PermissionResponse> create(@RequestBody @Valid PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .data(permissionService.create(request))
                .message("Create permission successfully")
                .build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "update permission by id")
    public ApiResponse<PermissionResponse> update(@PathVariable Integer id,
                                                  @RequestBody @Valid PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .data(permissionService.update(id, request))
                .message("Update permission successfully")
                .build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete permission by id")
    public ApiResponse<Void> delete(@PathVariable Integer id) {
        permissionService.delete(id);
        return ApiResponse.<Void>builder()
                .message("Delete permission successfully")
                .build();
    }
}
