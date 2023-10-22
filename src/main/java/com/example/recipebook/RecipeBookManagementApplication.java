package com.example.recipebook;

import com.example.recipebook.model.Author;
import com.example.recipebook.model.ContactInformation;
import com.example.recipebook.model.Recipe;
import com.example.recipebook.repository.AuthorRepository;
import com.example.recipebook.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.HashSet;
import java.util.Set;

/**
 * Main class for the RecipeBook Management Application.
 * Implements CommandLineRunner to populate the database with dummy data on application startup.
 */

@SpringBootApplication
public class RecipeBookManagementApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RecipeBookManagementApplication.class, args);
	}

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired RecipeRepository recipeRepository;

	@Override
	public void run(String... args) throws Exception {
		// This method is executed on application startup to insert dummy data into the database.

		// Dummy data for ContactInformation
		ContactInformation contactInfo = new ContactInformation();
		contactInfo.setPhoneNumber("123-456-7890");
		contactInfo.setEmailAddress("john.doe@example.com");
		contactInfo.setStreetAddress("123 Main St");
		contactInfo.setCity("Cityville");
		contactInfo.setState("New Jersey");
		contactInfo.setZipCode("12345");
		contactInfo.setCountry("United States of America");

		// Dummy data for Author
		Author author = new Author();
		author.setFirstName("John");
		author.setLastName("Doe");
		author.setContactInformation(contactInfo);

		// Dummy data for Recipe
		Recipe recipe = new Recipe();
		recipe.setRecipeName("Spaghetti Bolognese");
		recipe.setIngredients("Ground beef, tomatoes, pasta");
		recipe.setInstructions("Cook the beef, add tomatoes, and serve over pasta.");
		recipe.setAuthor(author);

		// Dummy data for another Recipe
		Recipe anotherRecipe = new Recipe();
		anotherRecipe.setRecipeName("Chicken Alfredo");
		anotherRecipe.setIngredients("Chicken, fettuccine pasta, Alfredo sauce");
		anotherRecipe.setInstructions("Cook chicken, prepare pasta, and mix with Alfredo sauce.");
		anotherRecipe.setAuthor(author);

		Set<Recipe> recipes = new HashSet<>();
		recipes.add(recipe);
		recipes.add(anotherRecipe);

		author.setRecipes(recipes);

		authorRepository.save(author);

		recipeRepository.save(recipe);
		recipeRepository.save(anotherRecipe);
	}
}
