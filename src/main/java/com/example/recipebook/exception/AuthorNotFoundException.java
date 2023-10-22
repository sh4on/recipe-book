package com.example.recipebook.exception;

public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException() {
    }

    public AuthorNotFoundException(String message) {
        super(message);
    }
}
