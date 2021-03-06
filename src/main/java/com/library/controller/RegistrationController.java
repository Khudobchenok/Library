package com.library.controller;

import com.library.entity.User;
import com.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;

/**
 * Создание контроллера RegistrationController
 */

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    /**
     * В GET запросе на страницу добавляется новый пустой объект класса User. Это сделано для того, чтобы при
     * POST запросе не доставать данные из формы регистрации по одному (username, password, passwordComfirm), а
     * сразу получить заполненный объект userForm.
     */

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    /*Аннотация Valid проверяет выполняются ли ограничения, установленные на
    поля, в данном случае длина не меньше 2 символов.*/
    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult,
                          Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }
        if (!userService.saveUser(userForm, model)) {
            return "registration";
        }

        return "redirect:/welcome";
    }
}
