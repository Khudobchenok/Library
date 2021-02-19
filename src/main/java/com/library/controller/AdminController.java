package com.library.controller;

import com.library.service.AuthorService;
import com.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Создание контроллера AdminController.
 * Доступ к странице admin имеют только пользователи с ролью администратора.
 */

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    AuthorService authorService;

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";
    }

    //Доступ к странице admin имеют только пользователи с ролью администратора
    @PostMapping("/admin")
    public String deleteUser(@RequestParam(required = true, defaultValue = "") Long userId,
                             @RequestParam(required = true, defaultValue = "") String action) {
        if (action.equals("delete")) {
            userService.deleteUser(userId);
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/gt/{userId}")
    public String gtUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("allUsers", userService.usergtList(userId));
        return "admin";
    }

    @GetMapping("/addAuthor")
    public String addAuthor (@RequestParam(value = "name") final String name,
                             @RequestParam(value = "biography") final String biography) {
        authorService.addAuthor(name, biography);
        return "admin";
    }
}
