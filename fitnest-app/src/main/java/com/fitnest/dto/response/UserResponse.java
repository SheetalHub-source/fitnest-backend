package com.fitnest.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long userId;
    private String name;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;

    private LocalDate joinDate;
}
