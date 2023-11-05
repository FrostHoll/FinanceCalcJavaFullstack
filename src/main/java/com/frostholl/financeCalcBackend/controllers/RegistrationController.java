package com.frostholl.financeCalcBackend.controllers;

import com.frostholl.financeCalcBackend.userRole.Role;
import com.frostholl.financeCalcBackend.user.User;
import com.frostholl.financeCalcBackend.user.UserService;
import com.frostholl.financeCalcBackend.userRole.UserRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRolesService userRolesService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        var userFromDB = userService.getUserByLogin(user.getLogin());
        if (userFromDB != null) {
            model.addAttribute("message", "Этот логин уже занят!");
            return "registration";
        }
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addNewUser(user);
        userRolesService.setUserRolesByUser(user, Collections.singleton(Role.USER));
        return "redirect:/login";
    }
}
