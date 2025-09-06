package com.tanpuh.kickshubservice.service.user;

import com.tanpuh.kickshubservice.dto.request.UserCreationRequest;
import com.tanpuh.kickshubservice.dto.request.UserUpdateRequest;
import com.tanpuh.kickshubservice.dto.response.UserResponse;
import com.tanpuh.kickshubservice.entity.User;
import com.tanpuh.kickshubservice.exception.AppException;
import com.tanpuh.kickshubservice.exception.ErrorCode;
import com.tanpuh.kickshubservice.mapper.UserMapper;
import com.tanpuh.kickshubservice.repository.UserRepository;
import com.tanpuh.kickshubservice.utils.enums.EntityStatus;
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
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserMapper mapper;

    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public UserResponse getById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return mapper.toResponse(user);
    }

    @Override
    public UserResponse create(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        User user = mapper.toEntity(request);
        user.setPhone(formatPhoneNumber(request.getPhone()));
        user.setStatus(EntityStatus.ACTIVE.getCode());

        return mapper.toResponse(userRepository.save(user));
    }

    @Override
    public UserResponse update(UserUpdateRequest request, Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        mapper.update(user, request);
        user.setPhone(formatPhoneNumber(request.getPhone()));

        return mapper.toResponse(userRepository.save(user));
    }

    @Override
    public void delete(Integer id) {
        userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        userRepository.deleteById(id);
    }

    private String formatPhoneNumber(String phone) {
        String digitsOnly = phone.replaceAll("[\\s\\-\\.]", "");

        if (digitsOnly.startsWith("+84"))
            digitsOnly = "0" + digitsOnly.substring(3);

        return digitsOnly;
    }
}
