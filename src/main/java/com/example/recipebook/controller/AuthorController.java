package com.example.recipebook.controller;

import com.example.recipebook.exception.AuthorNotFoundException;
import com.example.recipebook.model.Author;
import com.example.recipebook.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    // Handles displaying the author registration form.
    @GetMapping("/add")
    public String addAuthor(Model model) {
        Author author = new Author();
        model.addAttribute("author", author);

        return "authorRegistrationForm";
    }

    // Handles saving a new author.
    @PostMapping("/save")
    public String saveAuthor(@ModelAttribute Author author, RedirectAttributes attributes) {
        authorService.saveAuthor(author);

        String message = String.format("Author with ID %d saved successfully", author.getAuthorID());
        attributes.addAttribute("message", message);

        return "redirect:/authors";
    }

    // Handles displaying details of a single author.
    @GetMapping("/{id}")
    public String showAuthor(@PathVariable Long id, Model model, RedirectAttributes attributes) {
        String page;

        try {
            Author author = authorService.getAuthorById(id);
            model.addAttribute("author", author);

            page = "authorDetails";
        } catch (AuthorNotFoundException e) {
            attributes.addAttribute("message", e.getMessage());
            page = "redirect:/authors";
        }

        return page;
    }

    // Handles displaying a list of authors.
    @GetMapping
    public String showAllAuthors(@RequestParam(required = false) String message, Model model) {
        List<Author> authors = authorService.getAllAuthors();

        model.addAttribute("authors", authors);
        model.addAttribute("message", message);

        return "authorList";
    }

    // Handles displaying the author edit form.
    @GetMapping("/{id}/edit")
    public String editAuthor(@PathVariable Long id, Model model, RedirectAttributes attributes) {
        String page;

        try {
            Author author = authorService.getAuthorById(id);
            model.addAttribute("author", author);

            page = "editAuthor";
        } catch (AuthorNotFoundException e) {
            attributes.addAttribute("message", e.getMessage());
            page = "redirect:/authors";
        }

        return page;
    }

    // Handles updating an author's information.
    @PostMapping("/update")
    public String updateAuthor(@ModelAttribute Author author, RedirectAttributes attributes) {
        try {
            authorService.updateAuthor(author);

            String message = String.format("Author with ID %d updated successfully.", author.getAuthorID());
            attributes.addAttribute("message", message);
        } catch (AuthorNotFoundException e) {
            attributes.addAttribute("message", e.getMessage());
        }

        return "redirect:/authors";
    }

    // Handles deleting an author.
    @GetMapping("/{id}/delete")
    public String deleteAuthor(@PathVariable Long id, RedirectAttributes attributes) {
        try {
            authorService.deleteAuthorById(id);

            String message = String.format("Author with ID %d deleted Successfully.", id);
            attributes.addAttribute("message", message);
        } catch (AuthorNotFoundException e) {
            attributes.addAttribute("message", e.getMessage());
        }

        return "redirect:/authors";
    }
}
