package com.frostholl.financeCalcBackend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users/allUsers";
    }

    @GetMapping("/edit")
    public String editProfile(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "users/profile";
    }

    @PostMapping("/edit")
    public String saveChanges(@AuthenticationPrincipal User user,
                              @RequestParam(name = "login") String login,
                              @RequestParam(name = "user_surname") String surname,
                              @RequestParam(name = "user_name") String name) {
        user.setLogin(login);
        user.setUser_surname(surname);
        user.setUser_name(name);
        userService.updateUser(user);
        return "redirect:/home";
    }
}
