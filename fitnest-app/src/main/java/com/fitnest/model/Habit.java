package com.fitnest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(
        name = "habits"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Habit extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "habit_name", nullable = false, length = 120)
    private String habitName;

    @Column(name = "target_per_day")
    private Integer targetPerDay;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "habit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HabitLog> habitLogs;
}
