package com.frostholl.financeCalcBackend.expensesLimit;

import com.frostholl.financeCalcBackend.category.Category;
import com.frostholl.financeCalcBackend.user.User;
import jakarta.persistence.*;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name = "expenses_limit")
public class ExpensesLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "limit_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "limit_amount")
    private double limitAmount = 0d;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    public ExpensesLimit() {
    }

    public ExpensesLimit(User user, double limitAmount, Category category) {
        this.user = user;
        this.limitAmount = limitAmount;
        this.category = category;
    }

    public ExpensesLimit(Integer id, User user, double limitAmount, Category category) {
        this.id = id;
        this.user = user;
        this.limitAmount = limitAmount;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    public double getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(double limitAmount) {
        this.limitAmount = limitAmount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ExpensesLimit{" +
                "id=" + id +
                ", user=" + user +
                ", limitAmount=" + limitAmount +
                ", category=" + category +
                '}';
    }
}
