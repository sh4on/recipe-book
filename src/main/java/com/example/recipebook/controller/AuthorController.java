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

    // Handles displaying a list of authors.
    @GetMapping
    public String showAllAuthors(@RequestParam(required = false) String message, Model model) {
        List<Author> authors = authorService.getAllAuthors();

        model.addAttribute("authors", authors);
        model.addAttribute("message", message);

        return "authorList";
    }

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

        // Redirects to the author list with a success message after saving.
        return "redirect:/authors";
    }

    // Handles displaying details of a single author.
    @GetMapping("/{id}")
    public String showSingleAuthor(@PathVariable Long id, Model model, RedirectAttributes attributes) {
        try {
            Author author = authorService.getAuthorById(id);
            model.addAttribute("author", author);

            return "authorDetails";
        } catch (AuthorNotFoundException e) {
            attributes.addAttribute("message", e.getMessage());

            // Redirects to the author list with a message if the author is not found.
            return "redirect:/authors";
        }
    }

    // Handles displaying the author edit form.
    @GetMapping("/{id}/edit")
    public String editAuthor(@PathVariable Long id, Model model, RedirectAttributes attributes) {
        try {
            Author author = authorService.getAuthorById(id);
            model.addAttribute("author", author);

            return "editAuthor";
        } catch (AuthorNotFoundException e) {
            attributes.addAttribute("message", e.getMessage());

            // Redirects to the author list with a message if the author is not found.
            return "redirect:/authors";
        }
    }

    // Handles updating an author's information.
    @PostMapping("/update")
    public String updateAuthor(@ModelAttribute Author author, RedirectAttributes attributes) {
        try {
            authorService.updateAuthor(author);

            String message = String.format("Author with ID %d updated successfully.", author.getAuthorID());
            attributes.addAttribute("message", message);

            // Redirects to the author list with a message after updating.
            return "redirect:/authors";
        } catch (AuthorNotFoundException e) {
            attributes.addAttribute("message", e.getMessage());

            // Redirects to the author list with a message if the author is not found.
            return "redirect:/authors";
        }
    }

    // Handles deleting an author.
    @GetMapping("/{id}/delete")
    public String deleteAuthor(@PathVariable Long id, RedirectAttributes attributes) {
        try {
            authorService.deleteAuthorById(id);

            String message = String.format("Author with ID %d deleted Successfully.", id);
            attributes.addAttribute("message", message);

            // Redirects to the author list with a message after deleting.
            return "redirect:/authors";
        } catch (AuthorNotFoundException e) {
            attributes.addAttribute("message", e.getMessage());

            // Redirects to the author list with a message if the author is not found.
            return "redirect:/authors";
        }
    }
}
