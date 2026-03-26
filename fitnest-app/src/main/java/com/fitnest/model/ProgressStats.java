package com.fitnest.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "progress_stats",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_user_week",
                        columnNames = {"user_id", "week_start_date"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProgressStats extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_workouts")
    private Integer totalWorkouts;

    @Column(name = "total_calories_burned")
    private Integer totalCaloriesBurned;

    @Column(name = "week_start_date", nullable = false)
    private LocalDate weekStartDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserProfile user;
}
