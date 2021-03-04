package com.library.controller;

import com.library.entity.Author;
import com.library.error.Error;
import com.library.repository.AuthorRepo;
import com.library.repository.BookRepo;
import com.library.repository.UserRepo;
import com.library.service.AuthorService;
import com.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @GetMapping("/books/addNewAuthor")
    public String addAuthor(Model model) {
        model.addAttribute("authorForm", new Author());
        model.addAttribute("authorError", Error.getMessage(userRepo.findByUsername(userService.getCurrentUsername())));
        return "books/addNewAuthor";
    }

    @PostMapping("/books/addNewAuthor")
    public String addAuthor(@ModelAttribute("authorForm") @Valid Author authorForm, BindingResult bindingResult,
                            Model model) {
        if (bindingResult.hasErrors()) {
            return "books/addNewAuthor";
        }
        if (!authorService.addAuthor(authorForm, model)) {

            return "redirect:/books/addNewAuthor";
        }

        return "redirect:/books/addNewAuthor";
    }

    @GetMapping("/books/actualAuthor/{id}")
    public String actualAuthor(Model model, @PathVariable Long id) {
        model.addAttribute("author", authorRepo.findAuthorById(id));
        model.addAttribute("books", bookRepo.findBooksByAuthor_Id(id));
        return "books/actualAuthor/{id}";
    }
}
