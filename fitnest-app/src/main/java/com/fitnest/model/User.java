package com.fitnest.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(
        name = "users"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 120)
    private String name;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "fitness_goal", length = 100)
    private String fitnessGoal;

    @Column(name = "join_date")
    private LocalDate joinDate;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<WorkoutLog> workouts;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<DietLog> dietLogs;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Habit> habits;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<BodyMeasurement> measurements;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<AIRecommendation> recommendations;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ProgressStats> progressStats;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserSubscription> subscriptions;
}
