package com.fitnest.model;

import com.fitnest.enums.MealType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "diet_logs"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DietLog extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "log_date", nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "meal_type", nullable = false)
    private MealType mealType;

    @Column(name = "food_name", nullable = false, length = 150)
    private String foodName;

    @Column(name = "calories")
    private Integer calories;

    @Column(name = "protein_grams")
    private Double protein;

    @Column(name = "carbs_grams")
    private Double carbs;

    @Column(name = "fat_grams")
    private Double fat;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
