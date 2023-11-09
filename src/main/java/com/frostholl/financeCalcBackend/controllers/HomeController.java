package com.frostholl.financeCalcBackend.controllers;

import com.frostholl.financeCalcBackend.Category.CategoryService;
import com.frostholl.financeCalcBackend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal User user, Model model) {
        String name = user.getUser_surname() + " " + user.getUser_name();
        model.addAttribute("username", name);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "home";
    }
}
