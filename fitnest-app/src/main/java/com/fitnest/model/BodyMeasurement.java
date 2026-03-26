package com.fitnest.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "body_measurements"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BodyMeasurement extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "record_date", nullable = false)
    private LocalDate recordDate;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "height_cm")
    private Double height;

    @Column(name = "body_fat_percentage")
    private Double bodyFat;

    @Column(name = "muscle_mass")
    private Double muscleMass;

    @Column(name = "bmi")
    private Double bmi;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserProfile user;
}
