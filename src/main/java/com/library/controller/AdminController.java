package com.library.controller;

import com.library.entity.Author;
import com.library.entity.User;
import com.library.error.Error;
import com.library.repository.AuthorRepo;
import com.library.repository.BookRepo;
import com.library.repository.UserRepo;
import com.library.service.AuthorService;
import com.library.service.BookService;
import com.library.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        model.addAttribute("allAuthors", authorService.allAuthors());
        model.addAttribute("allBooks", bookService.allBooks());
        model.addAttribute("adminError", Error.getMessage(userRepo.findByUsername(userService.getCurrentUsername())));
        return "admin";
    }

    //Доступ к странице admin имеют только пользователи с ролью администратора
    @PostMapping("/admin/delete/user/{userId}")
    public String deleteUser(@PathVariable Long userId,
                             @RequestParam(required = true, defaultValue = "") String action) {
        if (action.equals("delete")) {
            userService.deleteUser(userId);
        }
        return "redirect:/admin";
    }
    @PostMapping("/admin/delete/book/{bookId}")
    public String deleteBook(@RequestParam(required = true, defaultValue = "") String action,
                             @PathVariable Long bookId) {
        if (action.equals("delete")) {
            bookService.deleteBook(bookId);
        }
        return "redirect:/admin";
    }
    @PostMapping("/admin/delete/author/{author:\\d+}")
    public String deleteAuthor(@PathVariable Author author,
                               @RequestParam(required = true, defaultValue = "") String action) {
        if (author.isValid()){
            if (action.equals("delete")) {
                authorRepo.delete(author);
            }
            return "redirect:/admin";
        }
        Error.setMessage(userRepo.findByUsername(userService.getCurrentUsername()), "Author has a book");
        return "redirect:/admin";
    }

    @PostMapping("/admin/giveAdmin/user/{userId}")
    public String addAdmin(@PathVariable Long userId,
                           @RequestParam(required = true, defaultValue = "") String action) {
        if (action.equals("newAdmin")) {
            userService.addAdmin(userId);
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/secret/startProject")
    public String startProject () {
        try {
            userService.addRoles();
            User user = userRepo.save(new User("admin","admin"));
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userService.addAdmin(user.getId());
            return "redirect:/welcome";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "redirect:/welcome";
        }
    }
}