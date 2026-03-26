package com.fitnest.service;

import com.fitnest.dto.request.CreateUserRequest;
import com.fitnest.dto.request.UpdateUserRequest;
import com.fitnest.dto.response.UserResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;

public interface UserService {
    UserResponse createUser(@Valid CreateUserRequest request);

    UserResponse updateUser(Long id, @Valid UpdateUserRequest request);

    UserResponse getUserByEmail(String email);

    List<UserResponse> getAllUsers();

    @Transactional
    void deleteUser(Long userId);
}
