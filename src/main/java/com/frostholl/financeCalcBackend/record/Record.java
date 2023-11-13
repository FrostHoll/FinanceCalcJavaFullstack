package com.frostholl.financeCalcBackend.record;

import com.frostholl.financeCalcBackend.category.Category;
import com.frostholl.financeCalcBackend.goal.Goal;
import com.frostholl.financeCalcBackend.loan.Loan;
import com.frostholl.financeCalcBackend.user.User;
import jakarta.persistence.*;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;

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

    @Transient
    private RecordType recordType = RecordType.OTHER;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loan_id")
    private Loan loan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "goal_id")
    private Goal goal;

    @Column(name = "record_date")
    private LocalDateTime recordDate;

    public Record() {
    }

    public Record(User user,
                  double amount,
                  String description,
                  Category category,
                  Loan loan,
                  Goal goal,
                  LocalDateTime recordDate) {
        this.user = user;
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.loan = loan;
        this.goal = goal;
        this.recordDate = recordDate;
    }

    public Record(Integer id,
                  User user,
                  double amount,
                  String description,
                  Category category,
                  Loan loan,
                  Goal goal,
                  LocalDateTime recordDate) {
        this.id = id;
        this.user = user;
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.loan = loan;
        this.goal = goal;
        this.recordDate = recordDate;
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
    public double getAmount() {
        setAutoRecordType();
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

    public LocalDateTime getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = LocalDateTime.parse(recordDate);
    }

    public void setRecord_date(LocalDateTime record_date) {
        this.recordDate = record_date;
    }

    public RecordType getRecordType() {
        return recordType;
    }

    public void setRecordType(RecordType recordType) {
        this.recordType = recordType;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
        recordType = RecordType.CATEGORY;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
        recordType = RecordType.LOAN;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
        recordType = RecordType.GOAL;
    }

    public String getRecordTypeInfo() {
        if (recordType == RecordType.OTHER)
            return "";
        return switch (recordType) {
            case CATEGORY -> category.getDescription();
            case LOAN -> loan.getLoanInfo();
            case GOAL -> goal.getGoalInfo();
            default -> "";
        };
    }

    private void setAutoRecordType() {
        if (category != null) {
            recordType = RecordType.CATEGORY;
            return;
        }
        if (loan != null) {
            recordType = RecordType.LOAN;
            return;
        }
        if (goal != null) {
            recordType = RecordType.GOAL;
            return;
        }
        recordType = RecordType.OTHER;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", user=" + user +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", recordType=" + recordType +
                ", category=" + category +
                ", loan=" + loan +
                ", goal=" + goal +
                ", recordDate=" + recordDate +
                '}';
    }
}
