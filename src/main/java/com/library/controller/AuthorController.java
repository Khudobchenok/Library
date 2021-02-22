package com.library.controller;

import com.library.entity.Author;
import com.library.entity.Book;
import com.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/addAuthor")
    public String addAuthor(Model model) {
        model.addAttribute("authorForm", new Author());
        return "addAuthor";
    }

    @PostMapping("/addAuthor")
    public String addAuthor(@ModelAttribute("authorForm") @Valid Author authorForm, BindingResult bindingResult,
                            Model model) {
        if (bindingResult.hasErrors()) {
            return "addAuthor";
        }
        if (!authorService.addAuthor(authorForm, model)) {

            return "addAuthor";
        }

        return "redirect:/books";
    }
}
