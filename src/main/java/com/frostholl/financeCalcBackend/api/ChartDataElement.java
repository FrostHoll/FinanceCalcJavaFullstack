package com.frostholl.financeCalcBackend.api;

class ChartDataElement {
    private String description;
    private Double sum;

    public ChartDataElement(String description, Double sum) {
        this.description = description;
        this.sum = sum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "ChartDataElement{" +
                "description='" + description + '\'' +
                ", sum=" + sum +
                '}';
    }
}
