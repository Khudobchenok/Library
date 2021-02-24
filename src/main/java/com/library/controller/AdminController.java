package com.library.controller;

import com.library.repository.BookRepo;
import com.library.repository.UserRepo;
import com.library.service.AuthorService;
import com.library.service.BookService;
import com.library.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Создание контроллера AdminController.
 * Доступ к странице admin имеют только пользователи с ролью администратора.
 */

@Slf4j
@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        model.addAttribute("allAuthors", authorService.allAuthors());
        model.addAttribute("allBooks", bookService.allBooks());
        return "admin";
    }

    //Доступ к странице admin имеют только пользователи с ролью администратора
    @PostMapping("/adminDeleteUser")
    public String deleteUser(@RequestParam(required = true, defaultValue = "") Long userId,
                             @RequestParam(required = true, defaultValue = "") String action) {
        if (action.equals("delete")) {
            userService.deleteUser(userId);
        }
        return "redirect:/admin";
    }
    @PostMapping("/adminDeleteBook")
    public String deleteBook(@RequestParam(required = true, defaultValue = "") Long bookId,
                             @RequestParam(required = true, defaultValue = "") String action) {
        if (action.equals("delete")) {
            bookService.deleteBook(bookId);
        }
        return "redirect:/admin";
    }
    @PostMapping("/adminDeleteAuthor")
    public String deleteAuthor(@RequestParam(required = true, defaultValue = "") Long authorId,
                               @RequestParam(required = true, defaultValue = "") String action) {
        if (bookRepo.findBooksByAuthor_Id(authorId).isEmpty()) {
            if (action.equals("delete")) {
                authorService.deleteAuthor(authorId);
            } else {
                log.info("Author have books");
                return "redirect:/admin";
            }
        }

        return "redirect:/admin";
    }

    @GetMapping("/admin/gt/{userId}")
    public String gtUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("allUsers", userService.usergtList(userId));
        return "admin";
    }

    @PostMapping("/addAdmin")
    public String addAdmin (@RequestParam(required = true, defaultValue = "") Long userId,
                            @RequestParam(required = true, defaultValue = "") String action) {
        if (action.equals("newAdmin")) {
            userService.addAdmin(userId);
        }
        return "redirect:/admin";
    }

    @GetMapping("/startProject")
    public String startProject () {
        Long userId = userRepo.findByUsername(userService.getCurrentUsername()).getId();
        try {
            userService.addRoles();
            userService.addAdmin(userId);
            return "redirect:/welcome";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "redirect:/welcome";
        }
    }
}