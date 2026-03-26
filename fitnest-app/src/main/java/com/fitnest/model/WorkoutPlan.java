package com.fitnest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(
        name = "workout_plans"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutPlan extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "workout_name", nullable = false, length = 150)
    private String workoutName;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "muscle_group", length = 100)
    private String muscleGroup;

    @Column(name = "duration_minutes")
    private Integer durationMinutes;

    @Column(name = "calories_estimate")
    private Integer caloriesEstimate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "trainer_id", nullable = false)
    private TrainerProfile trainer;

    @OneToMany(mappedBy = "workoutPlan", fetch = FetchType.LAZY)
    private List<WorkoutLog> workoutLogs;
}
