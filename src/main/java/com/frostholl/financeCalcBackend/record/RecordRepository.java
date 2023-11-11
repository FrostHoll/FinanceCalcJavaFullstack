package com.frostholl.financeCalcBackend.record;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RecordRepository extends JpaRepository<Record, Integer> {

    @Query("SELECT r FROM Record r where r.user.id = ?1")
    Optional<List<Record>> findByUserId(Integer id);
}
