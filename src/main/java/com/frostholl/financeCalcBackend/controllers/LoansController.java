package com.frostholl.financeCalcBackend.controllers;

import com.frostholl.financeCalcBackend.loan.LoanService;
import com.frostholl.financeCalcBackend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loans")
public class LoansController {

    @Autowired
    private LoanService loanService;

    @GetMapping
    public String getLoansView(@AuthenticationPrincipal User user,
                               Model model) {
        var loans = loanService.getLoansByUser(user);
        model.addAttribute("loans", loans);
        return "loan/loansView";
    }
}
