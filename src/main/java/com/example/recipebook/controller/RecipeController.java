package com.example.recipebook.controller;

import com.example.recipebook.exception.RecipeNotFoundException;
import com.example.recipebook.model.Author;
import com.example.recipebook.model.Recipe;
import com.example.recipebook.service.AuthorService;
import com.example.recipebook.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private AuthorService authorService;

    // Handles displaying the recipe creation form.
    @GetMapping("/add")
    public String addRecipe(Model model) {
        Recipe recipe = new Recipe();
        List<Author> authors = authorService.getAllAuthors();

        model.addAttribute("authors", authors);
        model.addAttribute("recipe", recipe);

        return "addRecipeForm";
    }

    // Handles saving a new recipe.
    @PostMapping("/save")
    public String saveRecipe(@ModelAttribute Recipe recipe, RedirectAttributes attributes) {
        recipeService.saveRecipe(recipe);

        String message = String.format("Recipe with ID %d saved successfully.", recipe.getRecipeID());
        attributes.addAttribute("message", message);

        return "redirect:/recipes";
    }

    // Handles displaying details of a single recipe.
    @GetMapping("/{id}")
    public String showRecipe(@PathVariable Long id, Model model, RedirectAttributes attributes) {
        String page;

        try {
            Recipe recipe = recipeService.getRecipeById(id);
            model.addAttribute("recipe", recipe);
            page = "recipeDetails";
        } catch (RecipeNotFoundException e) {
            attributes.addAttribute("message", e.getMessage());
            page = "redirect:/recipes";
        }

        return page;
    }

    // Handles displaying the list of recipes.
    @GetMapping
    public String showAllRecipes(@RequestParam(required = false) String message, Model model) {
        List<Recipe> recipes = recipeService.getAllRecipes();

        model.addAttribute("recipes", recipes);
        model.addAttribute("message", message);

        return "recipeList";
    }

    // Handles displaying the recipe edit form.
    @GetMapping("{id}/edit")
    public String editRecipe(@PathVariable Long id, Model model, RedirectAttributes attributes) {
        String page;

        try {
            Recipe recipe = recipeService.getRecipeById(id);
            List<Author> authors = authorService.getAllAuthors();

            model.addAttribute("recipe", recipe);
            model.addAttribute("authors", authors);

            page = "editRecipe";
        } catch (RecipeNotFoundException e) {
            attributes.addAttribute("message", e.getMessage());
            page = "redirect:/recipes";
        }

        return page;
    }

    // Handles updating a recipe's information.
    @PostMapping("/update")
    public String updateRecipe(@ModelAttribute Recipe recipe, RedirectAttributes attributes) {
        try {
            recipeService.updateRecipe(recipe);

            String message = String.format("Recipe with ID %d updated successfully", recipe.getRecipeID());
            attributes.addAttribute("message", message);
        } catch (RecipeNotFoundException e) {
            attributes.addAttribute("message", e.getMessage());
        }

        return "redirect:/recipes";
    }

    // Handles deleting a recipe.
    @GetMapping("/{id}/delete")
    public String deleteRecipe(@PathVariable Long id, Model model, RedirectAttributes attributes) {
        try {
            recipeService.deleteRecipeById(id);

            String message = String.format("Recipe with ID %d deleted successfully.", id);
            attributes.addAttribute("message", message);
        } catch (RecipeNotFoundException e) {
            attributes.addAttribute("message", e.getMessage());
        }

        return "redirect:/recipes";
    }
}
