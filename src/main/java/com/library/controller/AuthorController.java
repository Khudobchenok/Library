package com.library.controller;

import com.library.entity.Author;
import com.library.repository.AuthorRepo;
import com.library.repository.BookRepo;
import com.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    private BookRepo bookRepo;

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

    @GetMapping("/authorPage")
    public String actualAuthor(Model model, @RequestParam(value = "actualAuthor") Long id) {
        model.addAttribute("author", authorRepo.findAuthorById(id));
        model.addAttribute("books", bookRepo.findBooksByAuthor_Id(id));
        return "authorPage";
    }
}
