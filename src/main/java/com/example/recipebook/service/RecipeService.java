package com.example.recipebook.service;

import com.example.recipebook.model.Recipe;

import java.util.List;

public interface RecipeService {
    Recipe saveRecipe(Recipe recipe);

    List<Recipe> getAllRecipes();

    Recipe getRecipeById(Long id);

    void updateRecipe(Recipe recipe);

    void deleteRecipeById(Long id);
}
