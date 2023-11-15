package com.frostholl.financeCalcBackend.controllers;

import com.frostholl.financeCalcBackend.category.Category;
import com.frostholl.financeCalcBackend.category.CategoryService;
import com.frostholl.financeCalcBackend.expensesLimit.ExpensesLimit;
import com.frostholl.financeCalcBackend.expensesLimit.ExpensesLimitService;
import com.frostholl.financeCalcBackend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/limits")
public class LimitsController {

    @Autowired
    private ExpensesLimitService limitService;

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("categories")
    public List<Category> populateCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping
    public String showLimits(@AuthenticationPrincipal User user,
                             Model model) {
        var limitMap = new ArrayList<LimitMapElement>();
        for(var c: populateCategories()) {
            limitMap.add(new LimitMapElement(limitService.getUserLimitByCategory(user, c), c));
        }
        model.addAttribute("limitMap", limitMap);
        return "limit/limitsView";
    }

    @GetMapping("/edit/{limit}")
    public String editLimit(@AuthenticationPrincipal User user,
                            @PathVariable ExpensesLimit limit,
                            Model model) {
        if (!Objects.equals(limit.getUser().getId(), user.getId())) {
            return "redirect:/limits";
        }
        model.addAttribute("lim", limit);
        return "limit/addLimit";
    }

    @GetMapping("/add/{category}")
    public String showAddLimitPage(Category category,
                                   Model model) {
        var limit = new ExpensesLimit();
        limit.setCategory(category);
        model.addAttribute("lim", limit);
        return "limit/addLimit";
    }

    @PostMapping("/add")
    public String addLimit(@AuthenticationPrincipal User user,
                           ExpensesLimit limit) {
        limit.setUser(user);
        limitService.addOrUpdateLimit(user, limit);
        return "redirect:/limits";
    }
}

class LimitMapElement {
    private ExpensesLimit limit;

    private Category category;

    public LimitMapElement(ExpensesLimit limit, Category category) {
        this.limit = limit;
        this.category = category;
    }

    public ExpensesLimit getLimit() {
        return limit;
    }

    public void setLimit(ExpensesLimit limit) {
        this.limit = limit;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "LimitMapElement{" +
                "limit=" + limit +
                ", category=" + category +
                '}';
    }
}
