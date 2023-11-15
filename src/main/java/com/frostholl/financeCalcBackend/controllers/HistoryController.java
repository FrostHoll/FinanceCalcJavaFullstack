package com.frostholl.financeCalcBackend.controllers;

import com.frostholl.financeCalcBackend.category.Category;
import com.frostholl.financeCalcBackend.category.CategoryService;
import com.frostholl.financeCalcBackend.deposit.DepositService;
import com.frostholl.financeCalcBackend.expensesLimit.ExpensesLimitService;
import com.frostholl.financeCalcBackend.goal.GoalService;
import com.frostholl.financeCalcBackend.loan.LoanService;
import com.frostholl.financeCalcBackend.record.Record;
import com.frostholl.financeCalcBackend.record.RecordService;
import com.frostholl.financeCalcBackend.record.RecordType;
import com.frostholl.financeCalcBackend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private RecordService recordService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private LoanService loanService;

    @Autowired
    private GoalService goalService;

    @Autowired
    private DepositService depositService;

    @Autowired
    private ExpensesLimitService limitService;

    @ModelAttribute("allTypes")
    public List<RecordType> populateRecordTypes() {
        return Arrays.asList(RecordType.values());
    }

    @ModelAttribute("categories")
    public List<Category> populateCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping
    public String showRecords(@AuthenticationPrincipal User user,
                              Model model) {
        var records = recordService.getUserRecords(user);
        model.addAttribute("records", records);
        return "history/historyView";
    }

    @GetMapping("/add")
    public String showAddRecordPage(@AuthenticationPrincipal User user,
                                    Model model) {
        resetRecordModel(model, user);
        model.addAttribute("record", new Record());
        return "history/addRecord";
    }

    @PostMapping("/add")
    public String addRecord(@AuthenticationPrincipal User user,
                            Record record,
                            BindingResult bindingResult,
                            Model model) {
        if (bindingResult.hasErrors()) {
            resetRecordModel(model, user);
            record.setRecord_date(null);
            model.addAttribute("record", record);
            return "history/addRecord";
        }
        record.setUser(user);
        System.out.println(record.toString());
        try {
            recordService.addNewRecord(record);
        }
        catch (RuntimeException e) {
            var mes = e.getMessage();
            String notice = mes;
            if (mes.contains("min_balance"))
                notice = "Невозможно снять с вклада сумму, меньшую доступной.";
            if (mes.contains("full"))
                notice = "Переводимая сумма превышает сумму закрытия цели.";
            if (mes.contains("overflow"))
                notice = "Платеж больше суммы погашения кредита.";
            model.addAttribute("message", notice);
            resetRecordModel(model, user);
            record.setRecord_date(null);
            model.addAttribute("record", record);
            return "history/addRecord";
        }
        if (record.getCategory() != null && !checkLimit(user, record.getCategory())) {
            model.addAttribute("message",
                    String.format("Внимание! Запись была добавлена, но вы превысили лимит расходов по категории '%s' в этом месяце.", record.getCategory().getDescription()));
            resetRecordModel(model, user);
            record.setRecord_date(null);
            model.addAttribute("record", record);
            return "history/addRecord";
        }
        return "redirect:/history";
    }

    private boolean checkLimit(User user, Category category) {
        var limit = limitService.getUserLimitByCategory(user, category);
        if (limit == null) return true;
        var monthNow = LocalDateTime.now().getMonthValue();
        var yearNow = LocalDateTime.now().getYear();
        var records = recordService.getUserRecords(user).stream()
                .filter(r->r.getCategory() == category && r.getRecordDate().getMonthValue() == monthNow && r.getRecordDate().getYear() == yearNow).toList();
        var sum = 0d;
        for(var r: records) {
            sum += r.getAmount();
        }
        return sum < limit.getLimitAmount();
    }

    private void resetRecordModel(Model model, User user) {
        var loans = loanService.getLoansByUser(user);
        var goals = goalService.getGoalsByUser(user);
        var deposits = depositService.getDepositsByUser(user);
        model.addAttribute("loans", loans);
        model.addAttribute("goals", goals);
        model.addAttribute("deposits", deposits);
    }
}
