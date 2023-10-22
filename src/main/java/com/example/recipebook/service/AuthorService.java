package com.example.recipebook.service;

import com.example.recipebook.model.Author;

import java.util.List;

public interface AuthorService {

    Author saveAuthor(Author author);

    List<Author> getAllAuthors();

    Author getAuthorById(Long id);

    void updateAuthor(Author author);

    void deleteAuthorById(Long id);
}
