package com.frostholl.financeCalcBackend.controllers;

import com.frostholl.financeCalcBackend.loan.Loan;
import com.frostholl.financeCalcBackend.loan.LoanService;
import com.frostholl.financeCalcBackend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

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

    @GetMapping("/delete/{loan}")
    public String deleteLoan(@AuthenticationPrincipal User user,
                               Loan loan) {
        if (!Objects.equals(loan.getUser().getId(), user.getId()))
            return "redirect:/loans";
        loanService.deleteLoan(loan);
        return "redirect:/loans";
    }

    @GetMapping("/add")
    public String showAddLoanPage(Model model) {
        model.addAttribute("loan", new Loan());
        return "loan/addLoan";
    }

    @PostMapping("/add")
    public String addLoan(@AuthenticationPrincipal User user,
                          Loan loan,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "loan/addLoan";
        }
        loan.setInterestRate(loan.getInterestRate() / 100f);
        loan.setUser(user);
        System.out.println(loan.toString());
        loanService.addNewLoan(loan);
        return "redirect:/loans";
    }
}
