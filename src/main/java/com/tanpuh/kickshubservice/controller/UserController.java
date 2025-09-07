package com.tanpuh.kickshubservice.controller;

import com.tanpuh.kickshubservice.dto.request.UserCreationRequest;
import com.tanpuh.kickshubservice.dto.request.UserUpdateRequest;
import com.tanpuh.kickshubservice.dto.response.ApiResponse;
import com.tanpuh.kickshubservice.dto.response.UserResponse;
import com.tanpuh.kickshubservice.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "User Controller")
@Slf4j
public class UserController {
    UserService userService;

    @GetMapping
    @Operation(summary = "get list of users")
    public ApiResponse<List<UserResponse>> getAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return ApiResponse.<List<UserResponse>>builder()
                .data(userService.getAll())
                .message("Get list of users successfully")
                .build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "get user by id")
    public ApiResponse<UserResponse> getById(@PathVariable Integer id) {
        return ApiResponse.<UserResponse>builder()
                .data(userService.getById(id))
                .message("Get user successfully")
                .build();
    }

    @GetMapping("/my-info")
    @Operation(summary = "get user's info")
    public ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .data(userService.getMyInfo())
                .message("Get user's info successfully")
                .build();
    }

    @PostMapping
    @Operation(summary = "create new user")
    public ApiResponse<UserResponse> create(@RequestBody @Valid UserCreationRequest request) {
        return ApiResponse.<UserResponse>builder()
                .data(userService.create(request))
                .message("Create user successfully")
                .build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "update user by id")
    public ApiResponse<UserResponse> update(@PathVariable Integer id,
                                            @RequestBody @Valid UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .data(userService.update(id, request))
                .message("Update user successfully")
                .build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete user by id")
    public ApiResponse<Void> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ApiResponse.<Void>builder()
                .message("Delete user successfully")
                .build();
    }
}

