package com.frostholl.financeCalcBackend.loan;

import com.frostholl.financeCalcBackend.user.User;
import jakarta.persistence.*;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    private String description;

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
    private Double currentPayment;

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

//    public void setDateBegin(LocalDateTime dateBegin) {
//        this.dateBegin = dateBegin;
//    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = LocalDate.parse(dateEnd);
    }

//    public void setDateEnd(LocalDateTime dateEnd) {
//        this.dateEnd = dateEnd;
//    }

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
        this.dateLastPay = LocalDateTime.parse(dateLastPay);
    }

//    public void setDateLastPay(LocalDateTime dateLastPay) {
//        this.dateLastPay = dateLastPay;
//    }

    public String getLoanInfo() {
        return String.format("%s (%.0f)", description, loanAmount);
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
