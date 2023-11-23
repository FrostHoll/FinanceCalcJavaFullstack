package com.frostholl.financeCalcBackend.loan;

import com.frostholl.financeCalcBackend.user.User;
import jakarta.persistence.*;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

@Entity
@Table(name = "loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private String description = "Кредит";

    @Column(name = "loan_amount")
    private double loanAmount;

    @Column(name = "interest_rate")
    private float interestRate;

    private boolean annuity;

    private Double balance = 0.0d;

    @Column(name = "date_begin")
    private LocalDate dateBegin;

    @Column(name = "date_end")
    private LocalDate dateEnd;

    @Column(name = "current_payment")
    private Double currentPayment = 0d;

    @Column(name = "date_last_pay")
    private LocalDateTime dateLastPay;

    public Loan() {
    }

    public Loan(User user,
                String description,
                double loanAmount,
                float interestRate,
                boolean annuity,
                Double balance,
                LocalDate dateBegin,
                LocalDate dateEnd,
                Double currentPayment,
                LocalDateTime dateLastPay) {
        this.user = user;
        this.description = description;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.annuity = annuity;
        this.balance = balance;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.currentPayment = currentPayment;
        this.dateLastPay = dateLastPay;
    }

    public Loan(Integer id,
                User user,
                String description,
                double loanAmount,
                float interestRate,
                boolean annuity,
                Double balance,
                LocalDate dateBegin,
                LocalDate dateEnd,
                Double currentPayment,
                LocalDateTime dateLastPay) {
        this.id = id;
        this.user = user;
        this.description = description;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.annuity = annuity;
        this.balance = balance;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.currentPayment = currentPayment;
        this.dateLastPay = dateLastPay;
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
    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    @NumberFormat(pattern = "#0.0#%")
    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    public boolean isAnnuity() {
        return annuity;
    }

    public void setAnnuity(boolean annuity) {
        this.annuity = annuity;
    }

    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
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

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = LocalDate.parse(dateEnd);
    }

    public void setDate_End(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    public Double getCurrentPayment() {
        return currentPayment;
    }

    public void setCurrentPayment(Double currentPayment) {
        this.currentPayment = currentPayment;
    }

    public LocalDateTime getDateLastPay() {
        return dateLastPay;
    }

    public void setDateLastPay(String dateLastPay) {
        if (dateLastPay.isEmpty()) {
            this.dateLastPay = null;
            return;
        }
        this.dateLastPay = LocalDateTime.parse(dateLastPay);
    }

    public void setDate_Last_Pay(LocalDateTime dateLastPay) {
        this.dateLastPay = dateLastPay;
    }

    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    public Double getAmountWithPercents() {
        var months = dateBegin.until(dateEnd, ChronoUnit.MONTHS);
        return loanAmount * (1 + interestRate * months / 12.0d);
    }

    public String getLoanInfo() {
        if (currentPayment > 0)
            return String.format("%s (%.0f руб., тек. период - %.2f руб.)", description, getAmountWithPercents(), currentPayment);
        return String.format("%s (%.0f руб., погашено %.0f руб.)", description, getAmountWithPercents(), balance);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", user=" + user +
                ", description='" + description + '\'' +
                ", loanAmount=" + loanAmount +
                ", interestRate=" + interestRate +
                ", annuity=" + annuity +
                ", balance=" + balance +
                ", dateBegin=" + dateBegin +
                ", dateEnd=" + dateEnd +
                ", currentPayment=" + currentPayment +
                ", dateLastPay=" + dateLastPay +
                '}';
    }
}
