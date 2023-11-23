package com.frostholl.financeCalcBackend.controllers;

import com.frostholl.financeCalcBackend.deposit.Deposit;
import com.frostholl.financeCalcBackend.deposit.DepositService;
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

import java.util.Objects;

@Controller
@RequestMapping("/deposits")
public class DepositsController {

    @Autowired
    private final DepositService depositService;

    public DepositsController(DepositService depositService) {
        this.depositService = depositService;
    }

    @GetMapping
    public String getDepositsView(@AuthenticationPrincipal User user,
                                  Model model) {
        var deposits = depositService.getDepositsByUser(user);
        model.addAttribute("deposits", deposits);
        return "deposit/depositsView";
    }

    @GetMapping("/add")
    public String showAddDepositPage(Model model) {
        model.addAttribute("deposit", new Deposit());
        return "deposit/addDeposit";
    }

    @GetMapping("/delete/{deposit}")
    public String deleteDeposit(@AuthenticationPrincipal User user,
                               Deposit deposit) {
        if (!Objects.equals(deposit.getUser().getId(), user.getId()))
            return "redirect:/deposits";
        depositService.deleteDeposit(deposit);
        return "redirect:/deposits";
    }

    @PostMapping("/add")
    public String addDeposit(@AuthenticationPrincipal User user,
                             Deposit deposit,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "deposit/addDeposit";
        }
        deposit.setInterestRate(deposit.getInterestRate() / 100f);
        deposit.setUser(user);
        System.out.println(deposit);
        depositService.addNewDeposit(deposit);
        return "redirect:/deposits";
    }
}
