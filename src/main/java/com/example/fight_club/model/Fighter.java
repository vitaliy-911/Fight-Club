package com.example.fight_club.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "fighters")
public class Fighter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5, max = 50, message = " Name must be between 5 and 50 characters")
    @Column(nullable = false, length = 50)
    private String name;

    @Positive
    @Min(18)
    @Max(50)
    @Column(nullable = false)
    private int age;

    @Positive
    @Min(5)
    @Max(10)
    @Column(nullable = false)
    private int power;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    BeltColor beltColorFighter;

    @Positive
    @Min(0)
    @Max(10)
    @Column(nullable = false)
    double dodgeChance;

    private final int heilsFighters = 100;

    public Fighter() {
    }

    public Fighter(String name, int age, int power, BeltColor beltColorFighter, double dodgeChance) {
        this.name = name;
        this.age = age;
        this.power = power;
        this.beltColorFighter = beltColorFighter;
        this.dodgeChance = dodgeChance;
    }
}
