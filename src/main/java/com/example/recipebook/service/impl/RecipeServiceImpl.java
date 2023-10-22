package com.example.recipebook.service.impl;

import com.example.recipebook.exception.RecipeNotFoundException;
import com.example.recipebook.model.Recipe;
import com.example.recipebook.repository.RecipeRepository;
import com.example.recipebook.service.AuthorService;
import com.example.recipebook.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private AuthorService authorService;

    @Override
    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id).orElseThrow(
                () -> new RecipeNotFoundException(String.format("Recipe with ID %d not found!", id))
        );
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        // Before updating, checking whether the recipe exists in the database.
        getRecipeById(recipe.getRecipeID());

        recipe.setAuthor(authorService.getAuthorById(recipe.getAuthor().getAuthorID()));

        recipeRepository.save(recipe);
    }

    @Override
    public void deleteRecipeById(Long id) {
        recipeRepository.delete(getRecipeById(id));
    }
}
