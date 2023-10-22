package com.example.recipebook.exception;

public class RecipeNotFoundException extends RuntimeException {

    public RecipeNotFoundException() {
    }

    public RecipeNotFoundException(String message) {
        super(message);
    }
}
