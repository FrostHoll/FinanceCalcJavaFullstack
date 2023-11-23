package com.frostholl.financeCalcBackend.controllers;

import com.frostholl.financeCalcBackend.deposit.Deposit;
import com.frostholl.financeCalcBackend.goal.Goal;
import com.frostholl.financeCalcBackend.goal.GoalService;
import com.frostholl.financeCalcBackend.loan.Loan;
import com.frostholl.financeCalcBackend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Objects;

@Controller
@RequestMapping("/goals")
public class GoalsController {

    @Autowired
    private GoalService goalService;

    @GetMapping
    public String getGoalsView(@AuthenticationPrincipal User user,
                               Model model) {
        var goals = goalService.getGoalsByUser(user);
        model.addAttribute("goals", goals);
        return "goal/goalsView";
    }

    @GetMapping("/add")
    public String showAddGoalPage(Model model) {
        model.addAttribute("goal", new Goal());
        return "goal/addGoal";
    }

    @GetMapping("/delete/{goal}")
    public String deleteGoal(@AuthenticationPrincipal User user,
                             Goal goal) {
        if (!Objects.equals(goal.getUser().getId(), user.getId()))
            return "redirect:/goals";
        goalService.deleteGoal(goal);
        return "redirect:/goals";
    }

    @PostMapping("/add")
    public String addGoal(@AuthenticationPrincipal User user,
                          Goal goal,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "goal/addGoal";
        }
        goal.setUser(user);
        System.out.println(goal.toString());
        goalService.addNewGoal(goal);
        return "redirect:/goals";
    }
}
