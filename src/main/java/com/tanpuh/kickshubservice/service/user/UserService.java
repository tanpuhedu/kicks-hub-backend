package com.tanpuh.kickshubservice.service.user;

import com.tanpuh.kickshubservice.dto.request.UserCreationRequest;
import com.tanpuh.kickshubservice.dto.request.UserUpdateRequest;
import com.tanpuh.kickshubservice.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAll();
    UserResponse getById(Integer id);
    UserResponse getMyInfo();
    UserResponse create(UserCreationRequest request);
    UserResponse update(Integer id, UserUpdateRequest request);
    void delete(Integer id);
}
