package com.fitnest.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "user_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "auth_user_id", nullable = false, unique = true)
    private AuthUser authUser;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "join_date")
    private LocalDate joinDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id")
    private TrainerProfile trainer;

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

}
