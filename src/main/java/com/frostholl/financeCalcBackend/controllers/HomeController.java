package com.frostholl.financeCalcBackend.controllers;

import com.frostholl.financeCalcBackend.category.CategoryService;
import com.frostholl.financeCalcBackend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.GenericSqlQuery;
import org.springframework.jdbc.object.SqlFunction;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;

@Controller
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DataSource dataSource;

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal User user, Model model) {
        String name = user.getUser_surname() + " " + user.getUser_name();
        model.addAttribute("username", name);
        SqlQuery<Integer> query = new SqlFunction<Integer>(dataSource,"select check_recalculate();");
        query.execute();
        return "home";
    }
}
