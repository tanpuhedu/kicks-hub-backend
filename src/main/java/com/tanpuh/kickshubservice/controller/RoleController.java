package com.tanpuh.kickshubservice.controller;

import com.tanpuh.kickshubservice.dto.request.RoleRequest;
import com.tanpuh.kickshubservice.dto.response.ApiResponse;
import com.tanpuh.kickshubservice.dto.response.RoleResponse;
import com.tanpuh.kickshubservice.service.role.RoleService;
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
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Role Controller")
@Slf4j
public class RoleController {
    RoleService roleService;

    @GetMapping
    @Operation(summary = "get list of roles")
    ApiResponse<List<RoleResponse>> getAll() {
        return ApiResponse.<List<RoleResponse>>builder()
                .data(roleService.getAll())
                .message("Get list of roles successfully")
                .build();
    }

    @PostMapping
    @Operation(summary = "create new role")
    ApiResponse<RoleResponse> create(@RequestBody @Valid RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .data(roleService.create(request))
                .message("Create role successfully")
                .build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "update role by id")
    ApiResponse<RoleResponse> update( @PathVariable Integer id, @RequestBody @Valid RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .data(roleService.update(id, request))
                .message("Update role successfully")
                .build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete role by id")
    ApiResponse<Void> delete(@PathVariable Integer id) {
        roleService.delete(id);
        return ApiResponse.<Void>builder()
                .message("Delete role successfully")
                .build();
    }
}