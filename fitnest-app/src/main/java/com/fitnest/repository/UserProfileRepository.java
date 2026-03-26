package com.fitnest.repository;

import com.fitnest.model.AuthUser;
import com.fitnest.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByAuthUser(AuthUser authUser);
    List<UserProfile> findAllByIsDeletedFalse();

    Optional<UserProfile> findByIdAndIsDeletedFalse(Long userId);
}
