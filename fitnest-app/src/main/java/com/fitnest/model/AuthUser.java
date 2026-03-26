package com.fitnest.model;
import com.fitnest.enums.AuthProvider;
import com.fitnest.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "auth_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthUser extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password; // null for OAuth

    @Column(name = "full_name", nullable = false)
    private String name;

    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role; // ADMIN, USER, TRAINER

    @Enumerated(EnumType.STRING)
    @Column(name = "auth_provider")
    private AuthProvider authProvider; // LOCAL, GOOGLE

    @Column(name = "active", nullable = false)
    private Boolean active = true;
}
