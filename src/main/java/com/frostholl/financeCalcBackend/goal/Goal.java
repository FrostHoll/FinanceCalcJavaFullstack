package com.frostholl.financeCalcBackend.goal;

import com.frostholl.financeCalcBackend.user.User;
import jakarta.persistence.*;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "goal")
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goal_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "goal_name")
    private String goalName = "Цель";

    @Column(name = "goal_amount")
    private double goalAmount = 0d;

    @Column(name = "current_amount")
    private double currentAmount = 0d;

    @Column(name = "last_pay_date")
    private LocalDateTime dateLastPay;

    @Column(name = "min_pay")
    private Double minPay;

    public Goal() {
    }

    public Goal(User user, String goalName, double goalAmount, Double currentAmount, LocalDateTime dateLastPay, Double minPay) {
        this.user = user;
        this.goalName = goalName;
        this.goalAmount = goalAmount;
        this.currentAmount = currentAmount;
        this.dateLastPay = dateLastPay;
        this.minPay = minPay;
    }

    public Goal(Integer id, User user, String goalName, double goalAmount, Double currentAmount, LocalDateTime dateLastPay, Double minPay) {
        this.id = id;
        this.user = user;
        this.goalName = goalName;
        this.goalAmount = goalAmount;
        this.currentAmount = currentAmount;
        this.dateLastPay = dateLastPay;
        this.minPay = minPay;
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

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    public double getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(double goalAmount) {
        this.goalAmount = goalAmount;
    }

    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public LocalDateTime getDateLastPay() {
        return dateLastPay;
    }

    public void setDateLastPay(String dateLastPay) {
        if(dateLastPay.isEmpty()) {
            this.dateLastPay = null;
            return;
        }
        this.dateLastPay = LocalDateTime.parse(dateLastPay);
    }

    public void setLastPay_Date(LocalDateTime lastPayDate) {
        this.dateLastPay = lastPayDate;
    }

    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    public Double getMinPay() {
        return minPay;
    }

    public void setMinPay(Double minPay) {
        this.minPay = minPay;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "id=" + id +
                ", user=" + user +
                ", goalName='" + goalName + '\'' +
                ", goalAmount=" + goalAmount +
                ", currentAmount=" + currentAmount +
                ", dateLastPay=" + dateLastPay +
                ", minPay=" + minPay +
                '}';
    }

    public String getGoalInfo() {
        return String.format("%s (%.0f/%.0f)", goalName, currentAmount, goalAmount);
    }
}
