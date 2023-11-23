package com.frostholl.financeCalcBackend.api;

import com.frostholl.financeCalcBackend.category.CategoryService;
import com.frostholl.financeCalcBackend.deposit.DepositService;
import com.frostholl.financeCalcBackend.goal.GoalService;
import com.frostholl.financeCalcBackend.loan.LoanService;
import com.frostholl.financeCalcBackend.record.RecordService;
import com.frostholl.financeCalcBackend.user.User;
import jakarta.persistence.TemporalType;
import org.hibernate.mapping.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Types;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class RequestsController {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private RecordService recordService;

    @PostMapping("/chartData")
    public ResponseEntity<List<ChartDataElement>> test(@AuthenticationPrincipal User user) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("get_chart_data")
                .declareParameters(new SqlParameter[] { new SqlParameter("uid", Types.INTEGER),
                        new SqlParameter("months", Types.INTEGER)})
                .returningResultSet("elements",
                        BeanPropertyRowMapper.newInstance(ChartDataElement.class));
        Map m = call.execute(user.getId(), 1);
        List els = (List) m.get("elements");
        return ResponseEntity.ok(els);
    }

    @GetMapping("/chartData")
    public ResponseEntity<List<ChartDataElement>> chartData(@AuthenticationPrincipal User user) {
        var elements = getElements(user);
        return ResponseEntity.ok(elements);
    }

    private List<ChartDataElement> getElements(User user) {
        ArrayList<ChartDataElement> list = new ArrayList<>();
        var monthNow = LocalDateTime.now().getMonthValue();
        var yearNow = LocalDateTime.now().getYear();
        var records = recordService.getUserRecords(user).stream()
                .filter(r -> r.getRecordDate().until(LocalDateTime.now(), ChronoUnit.MONTHS) < 1).toList();
        Map<String, Double> cs = new HashMap<>();
        for(var r: records.stream().filter(r -> r.getCategory() != null).toList()) {
            var category = r.getCategory();
            if (cs.containsKey(category.getDescription()))
                cs.put(category.getDescription(), cs.get(category.getDescription()) + r.getAmount());
            else
                cs.put(category.getDescription(), r.getAmount());
        }
        cs.forEach((key, val) -> list.add(new ChartDataElement(key, val)));
        Map<String, Double> ls = new HashMap<>();
        for(var r: records.stream().filter(r -> r.getLoan() != null).toList()) {
            var loan = r.getLoan();
            if (ls.containsKey(loan.getDescription()))
                ls.put(loan.getDescription(), ls.get(loan.getDescription()) + r.getAmount());
            else
                ls.put(loan.getDescription(), r.getAmount());
        }
        ls.forEach((key, val) -> list.add(new ChartDataElement(key, val)));
        Map<String, Double> gs = new HashMap<>();
        for(var r: records.stream().filter(r -> r.getGoal() != null).toList()) {
            var goal = r.getGoal();
            if (gs.containsKey(goal.getGoalName()))
                gs.put(goal.getGoalName(), gs.get(goal.getGoalName()) + r.getAmount());
            else
                gs.put(goal.getGoalName(), r.getAmount());
        }
        gs.forEach((key, val) -> list.add(new ChartDataElement(key, val)));
        return list;
    }
}