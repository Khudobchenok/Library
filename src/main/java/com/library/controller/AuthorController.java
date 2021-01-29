package com.library.controller;

import com.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorController {

    AuthorService authorService;

    @GetMapping("/addAuthor")
    public String addAuthor (@RequestParam(value = "name") final String name,
                             @RequestParam(value = "biography") final String biography) {
        authorService.addAuthor(name, biography);
        return "admin";
    }
}
