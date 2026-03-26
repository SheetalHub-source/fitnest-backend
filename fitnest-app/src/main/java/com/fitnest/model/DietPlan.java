package com.fitnest.model;

import com.fitnest.enums.MealType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "diet_plans", indexes = {@Index(name = "idx_dietplan_trainer", columnList = "trainer_id")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DietPlan extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @Column(length = 300)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "trainer_id", nullable = false)
    private TrainerProfile trainer;
}
