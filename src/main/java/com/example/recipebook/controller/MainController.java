package com.example.recipebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    // This method handles requests for the home page.
    @GetMapping
    public String showHomePage() {
        return "home";
    }
}
