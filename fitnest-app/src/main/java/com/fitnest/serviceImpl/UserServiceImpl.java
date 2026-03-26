package com.fitnest.serviceImpl;

import com.fitnest.dto.request.CreateUserRequest;
import com.fitnest.dto.request.UpdateUserRequest;
import com.fitnest.dto.response.UserResponse;
import com.fitnest.enums.AuthProvider;
import com.fitnest.enums.Role;
import com.fitnest.exception.ResourceNotFoundException;
import com.fitnest.model.AuthUser;
import com.fitnest.model.UserProfile;
import com.fitnest.repository.AuthUserRepository;
import com.fitnest.repository.UserProfileRepository;
import com.fitnest.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthUserRepository authUserRepository;
    private final UserProfileRepository userProfileRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponse createUser(CreateUserRequest request) {

        String email = request.getEmail().trim().toLowerCase();
        String name = request.getName().trim();

        if (authUserRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists");
        }

        AuthUser authUser = AuthUser.builder()
                .name(name)
                .email(email)
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .role(Role.USER)
                .authProvider(AuthProvider.LOCAL)
                .active(true)
                .build();

        authUserRepository.save(authUser);

        UserProfile profile = UserProfile.builder()
                .authUser(authUser)
                .dateOfBirth(request.getDateOfBirth())
                .joinDate(LocalDate.now())
                .build();

        userProfileRepository.save(profile);

        log.info("User created with id: {}", profile.getId());

        return mapToResponse(profile, authUser);
    }
    @Transactional
    public UserResponse updateUser(Long userId, UpdateUserRequest request) {

        log.info("Updating user with id: {}", userId);

        UserProfile profile = userProfileRepository
                .findByIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        AuthUser authUser = profile.getAuthUser();

        if (request.getName() != null && !request.getName().trim().isEmpty()) {
            authUser.setName(request.getName().trim());
        }

        if (request.getPhone() != null && !request.getPhone().trim().isEmpty()) {
            authUser.setPhone(request.getPhone().trim());
        }

        if (request.getDateOfBirth() != null) {
            profile.setDateOfBirth(request.getDateOfBirth());
        }

        authUserRepository.save(authUser);
        userProfileRepository.save(profile);

        log.info("User updated successfully with id: {}", userId);

        return mapToResponse(profile, authUser);
    }


    @Override
    public UserResponse getUserByEmail(String email) {

        String normalizedEmail = email.trim().toLowerCase();

        AuthUser authUser = authUserRepository
                .findByEmailAndIsDeletedFalse(normalizedEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        UserProfile profile = userProfileRepository
                .findByAuthUser(authUser)
                .orElseThrow(() -> new ResourceNotFoundException("User profile not found"));

        return mapToResponse(profile, authUser);
    }

    @Override
    public List<UserResponse> getAllUsers() {

        List<UserProfile> users = userProfileRepository.findAllByIsDeletedFalse();

        return users.stream()
                .map(user -> mapToResponse(user, user.getAuthUser()))
                .toList();
    }

    @Transactional
    @Override
    public void deleteUser(Long userId) {

        UserProfile profile = userProfileRepository
                .findByIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        AuthUser authUser = profile.getAuthUser();

        profile.setIsDeleted(true);
        authUser.setIsDeleted(true);

        log.info("User soft deleted with id: {}", userId);
    }

    private UserResponse mapToResponse(UserProfile profile, AuthUser authUser) {
        return UserResponse.builder()
                .userId(profile.getId())
                .name(authUser.getName())
                .email(authUser.getEmail())
                .phone(authUser.getPhone())
                .dateOfBirth(profile.getDateOfBirth())
                .joinDate(profile.getJoinDate())
                .build();
    }
}
