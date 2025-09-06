package com.tanpuh.kickshubservice.service.user;

import com.tanpuh.kickshubservice.dto.request.UserCreationRequest;
import com.tanpuh.kickshubservice.dto.request.UserUpdateRequest;
import com.tanpuh.kickshubservice.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAll();
    UserResponse getById(Integer id);
    UserResponse create(UserCreationRequest dto);
    UserResponse update(UserUpdateRequest dto, Integer id);
    void delete(Integer id);
}
