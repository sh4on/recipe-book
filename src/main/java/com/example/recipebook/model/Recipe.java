package com.example.recipebook.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Long recipeID;

    @Column(name = "recipe_name", length = 100, nullable = false)
    private String recipeName;

    @Column(length = 150, nullable = false)
    private String ingredients;

    @Column(length = 250, nullable = false)
    private String instructions;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
}
