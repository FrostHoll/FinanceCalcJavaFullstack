package com.frostholl.financeCalcBackend.controllers;

import com.frostholl.financeCalcBackend.category.Category;
import com.frostholl.financeCalcBackend.category.CategoryService;
import com.frostholl.financeCalcBackend.loan.LoanService;
import com.frostholl.financeCalcBackend.record.Record;
import com.frostholl.financeCalcBackend.record.RecordService;
import com.frostholl.financeCalcBackend.record.RecordType;
import com.frostholl.financeCalcBackend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
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
        var loans = loanService.getLoansByUser(user);
        model.addAttribute("record", new Record());
        model.addAttribute("loans", loans);
        return "history/addRecord";
    }

    @PostMapping("/add")
    public String addRecord(@AuthenticationPrincipal User user,
                            Record record,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "history/addRecord";
        }
        record.setUser(user);
        System.out.println(record.toString());
        recordService.addNewRecord(record);
        return "redirect:/history";
    }
}
