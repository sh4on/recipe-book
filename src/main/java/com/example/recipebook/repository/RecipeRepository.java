package com.example.recipebook.repository;

import com.example.recipebook.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    // SQL: SELECT * FROM recipes WHERE recipe_name LIKE '%recipeName%'
    // HQL: FROM Recipe WHERE recipeName LIKE :recipeName
    List<Recipe> findByRecipeNameContaining(String recipeName);
}
