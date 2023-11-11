package com.frostholl.financeCalcBackend.record;

import com.frostholl.financeCalcBackend.user.User;
import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "record")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private double amount;

    private String description;

    private ZonedDateTime record_date;

    public Record() {
    }

    public Record(User user, double amount, String description, ZonedDateTime record_date) {
        this.user = user;
        this.amount = amount;
        this.description = description;
        this.record_date = record_date;
    }

    public Record(Integer id, User user, double amount, String description, ZonedDateTime record_date) {
        this.id = id;
        this.user = user;
        this.amount = amount;
        this.description = description;
        this.record_date = record_date;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ZonedDateTime getRecord_date() {
        return record_date;
    }

    public void setRecord_date(ZonedDateTime record_date) {
        this.record_date = record_date;
    }
}
