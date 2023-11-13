package com.frostholl.financeCalcBackend.goal;

import com.frostholl.financeCalcBackend.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
