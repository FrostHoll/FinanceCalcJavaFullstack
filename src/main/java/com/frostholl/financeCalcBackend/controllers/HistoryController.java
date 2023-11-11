package com.frostholl.financeCalcBackend.controllers;

import com.frostholl.financeCalcBackend.record.RecordService;
import com.frostholl.financeCalcBackend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private RecordService recordService;

    @GetMapping
    public String showRecords(@AuthenticationPrincipal User user,
                              Model model) {
        var records = recordService.getUserRecords(user);
        model.addAttribute("records", records);
        return "history/historyView";
    }
}
