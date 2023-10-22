package com.example.recipebook.service.impl;

import com.example.recipebook.exception.AuthorNotFoundException;
import com.example.recipebook.model.Author;
import com.example.recipebook.repository.AuthorRepository;
import com.example.recipebook.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(
                () -> new AuthorNotFoundException(String.format("Author with ID %d not found!", id))
        );
    }

    @Override
    public void updateAuthor(Author updatedAuthor) {
        // Before updating, checking whether the author exists in the database.
        getAuthorById(updatedAuthor.getAuthorID());

        authorRepository.save(updatedAuthor);
    }

    @Override
    public void deleteAuthorById(Long id) {
        authorRepository.delete(getAuthorById(id));
    }
}
