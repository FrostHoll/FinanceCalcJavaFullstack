package com.frostholl.financeCalcBackend.goal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GoalRepository extends JpaRepository<Goal, Integer> {

    @Query("SELECT g from Goal g where g.user.id = ?1")
    Optional<List<Goal>> getGoalsByUserId (Integer id);
}
