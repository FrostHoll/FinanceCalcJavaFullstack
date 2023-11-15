package com.frostholl.financeCalcBackend.expensesLimit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ExpensesLimitRepository extends JpaRepository<ExpensesLimit, Integer> {

    @Query("SELECT e from ExpensesLimit e where e.user.id = ?1")
    Optional<List<ExpensesLimit>> getLimitsByUserId(Integer id);

    @Query("select e from ExpensesLimit e where e.user.id = ?1 and e.category.id = ?2")
    Optional<ExpensesLimit> getUserLimitByCategoryId(Integer userId, Integer categoryId);
}
