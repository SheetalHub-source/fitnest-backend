package com.fitnest.config;

import com.fitnest.model.AuthUser;
import com.fitnest.repository.AuthUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final AuthUserRepository authUserRepository;

    public CustomUserDetailsService(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AuthUser> authUser = authUserRepository.findByEmail(email);
        if (authUser.isPresent()) {
            AuthUser a = authUser.get();

            return org.springframework.security.core.userdetails.User.builder()
                    .username(a.getEmail())
                    .password(a.getPassword())
                    .roles(String.valueOf(a.getRole()))
                    .build();
        }
        throw new UsernameNotFoundException("USER NOT FOUND");

    }
}
