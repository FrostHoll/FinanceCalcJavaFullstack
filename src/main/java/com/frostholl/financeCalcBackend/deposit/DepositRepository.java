package com.frostholl.financeCalcBackend.deposit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DepositRepository extends JpaRepository<Deposit, Integer> {

    @Query("SELECT d from Deposit d where d.user.id = ?1")
    Optional<List<Deposit>> getDepositsByUserId(Integer id);
}
