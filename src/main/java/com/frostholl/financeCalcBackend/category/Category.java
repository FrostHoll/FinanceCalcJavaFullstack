package com.frostholl.financeCalcBackend.category;

import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer id;

    private String description;

    public Category() {
    }

    public Category(String description) {
        this.description = description;
    }

    public Category(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "description='" + description + '\'' +
                '}';
    }
}
