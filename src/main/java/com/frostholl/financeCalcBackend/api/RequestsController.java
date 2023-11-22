package com.frostholl.financeCalcBackend.api;

import com.frostholl.financeCalcBackend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlFunction;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;


@RestController
@RequestMapping("/api")
public class RequestsController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/test")
    public ResponseEntity<List<ChartDataElement>> test(@AuthenticationPrincipal User user) {
        return null;
//        SqlQuery<ChartDataElement> query =
//                new SqlFunction<>(dataSource,"select * from get_chart_data();");
//        query.declareParameter(new SqlParameter("uid", Types.INTEGER));
//        List<ChartDataElement> output = query.execute(user.getId());
//        output.forEach(System.out::println);
//        return ResponseEntity.ok(output);
    }
}