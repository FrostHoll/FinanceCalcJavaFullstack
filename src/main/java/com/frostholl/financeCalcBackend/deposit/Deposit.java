package com.frostholl.financeCalcBackend.deposit;

import com.frostholl.financeCalcBackend.user.User;
import jakarta.persistence.*;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;

@Entity
@Table(name = "deposit")
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deposit_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private String description = "Вклад";

    @Column(name = "deposit_amount")
    private double depositAmount = 0d;

    @Column(name = "interest_rate")
    private float interestRate = 0f;

    @Column(name = "date_begin")
    private LocalDate dateBegin;

    private Double revenue = 0d;

    @Column(name = "min_balance")
    private Double minBalance = 0d;

    public Deposit() {
    }

    public Deposit(User user,
                   String description,
                   double depositAmount,
                   float interestRate,
                   LocalDate dateBegin,
                   Double revenue,
                   Double minBalance) {
        this.user = user;
        this.description = description;
        this.depositAmount = depositAmount;
        this.interestRate = interestRate;
        this.dateBegin = dateBegin;
        this.revenue = revenue;
        this.minBalance = minBalance;
    }

    public Deposit(Integer id,
                   User user,
                   String description,
                   double depositAmount,
                   float interestRate,
                   LocalDate dateBegin,
                   Double revenue,
                   Double minBalance) {
        this.id = id;
        this.user = user;
        this.description = description;
        this.depositAmount = depositAmount;
        this.interestRate = interestRate;
        this.dateBegin = dateBegin;
        this.revenue = revenue;
        this.minBalance = minBalance;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    @NumberFormat(pattern = "#0.0#%")
    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    public LocalDate getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(String dateBegin) {
        this.dateBegin = LocalDate.parse(dateBegin);
    }

    public void setDate_Begin(LocalDate dateBegin) {
        this.dateBegin = dateBegin;
    }

    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }

    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    public Double getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(Double minBalance) {
        this.minBalance = minBalance;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "id=" + id +
                ", user=" + user +
                ", description='" + description + '\'' +
                ", depositAmount=" + depositAmount +
                ", interestRate=" + interestRate +
                ", dateBegin=" + dateBegin +
                ", revenue=" + revenue +
                ", minBalance=" + minBalance +
                '}';
    }

    public String getDepositInfo() {
        return String.format("%s (%.0f руб., доступно %.0f руб.)", description, depositAmount, depositAmount + revenue - minBalance);
    }
}
