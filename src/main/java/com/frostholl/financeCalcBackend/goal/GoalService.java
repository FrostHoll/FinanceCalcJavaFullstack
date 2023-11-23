package com.frostholl.financeCalcBackend.goal;

import com.frostholl.financeCalcBackend.user.User;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;
import java.util.Map;

@Service
public class GoalService {
    private final GoalRepository goalRepository;

    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public List<Goal> getGoalsByUser(User user) {
        var goals = goalRepository.getGoalsByUserId(user.getId());
        return goals.orElse(null);
    }

    public void addNewGoal(Goal goal) {
        goalRepository.save(goal);
    }

    public void deleteGoal(Goal goal) {
        goalRepository.delete(goal);
    }
}
