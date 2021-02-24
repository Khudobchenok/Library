package com.library.controller;

import com.library.entity.Book;
import com.library.entity.User;
import com.library.repository.BookRepo;
import com.library.service.AuthorService;
import com.library.service.BookService;
import com.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookRepo bookRepo;

    @GetMapping("/books")
    public String userList(Model model) {
        model.addAttribute("allBooks", bookService.allBooks());
        model.addAttribute("allAuthors", authorService.allAuthors());
        return "books";
    }
    @GetMapping("/addBook")
    public String addBook(Model model) {
        model.addAttribute("bookForm", new Book());
        model.addAttribute("allAuthors", authorService.allAuthors());
        return "addBook";
    }

    @PostMapping("/addBook")
    public String addBook(@ModelAttribute("bookForm") @Valid Book bookForm, BindingResult bindingResult,
                          Model model) {
        if (bindingResult.hasErrors()) {
            return "addBook";
        }
        if (!bookService.addBook(bookForm, model)) {
            return "redirect:/addBook";
        }

        return "redirect:/books";
    }

    @GetMapping("/bookPage")
    public String actualBook(Model model, @RequestParam(value = "actualBook") Long id)  {
        model.addAttribute("book", bookRepo.findBookById(id));
        return "bookPage";
    }
}
