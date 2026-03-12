package com.fitnest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(
        name = "trainers"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trainer extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "trainer_name", nullable = false, length = 120)
    private String name;

    @Column(name = "specialization", length = 120)
    private String specialization;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "experience_years")
    private Integer experienceYears;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "trainer", fetch = FetchType.LAZY)
    private List<User> users;
}
