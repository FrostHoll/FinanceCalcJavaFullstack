package com.frostholl.financeCalcBackend.expensesLimit;

import com.frostholl.financeCalcBackend.category.Category;

public class LimitMapElement {
    private ExpensesLimit limit;

    private Category category;

    public LimitMapElement(ExpensesLimit limit, Category category) {
        this.limit = limit;
        this.category = category;
    }

    public ExpensesLimit getLimit() {
        return limit;
    }

    public void setLimit(ExpensesLimit limit) {
        this.limit = limit;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "LimitMapElement{" +
                "limit=" + limit +
                ", category=" + category +
                '}';
    }
}
