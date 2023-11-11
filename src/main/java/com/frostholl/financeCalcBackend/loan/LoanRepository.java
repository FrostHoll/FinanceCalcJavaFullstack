package com.frostholl.financeCalcBackend.loan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Integer> {

    @Query("SELECT l from Loan l where l.user.id = ?1")
    Optional<List<Loan>> getLoansByUserId(Integer id);
}
