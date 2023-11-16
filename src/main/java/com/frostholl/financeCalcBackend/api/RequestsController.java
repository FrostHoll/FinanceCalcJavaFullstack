package com.frostholl.financeCalcBackend.api;

import com.frostholl.financeCalcBackend.record.Record;
import com.frostholl.financeCalcBackend.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class RequestsController {

    @GetMapping("/test")
    public ResponseEntity<User> test(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(user);
    }
}
