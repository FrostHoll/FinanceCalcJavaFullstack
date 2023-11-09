package com.frostholl.financeCalcBackend.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("SELECT c from Category c WHERE c.id = ?1")
    Optional<Category> findCategoryById(Integer id);
}
