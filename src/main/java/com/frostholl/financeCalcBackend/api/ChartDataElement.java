package com.frostholl.financeCalcBackend.api;

public class ChartDataElement {
    private String desc;
    private Double sum;

    public ChartDataElement(String desc, Double sum) {
        this.desc = desc;
        this.sum = sum;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
                "description='" + desc + '\'' +
                ", sum=" + sum +
                '}';
    }
}
