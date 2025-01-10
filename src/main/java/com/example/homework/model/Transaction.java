package com.example.homework.model;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
    private int customerId;
    private Date transactionDate;
    private BigDecimal transactionAmount;

    public Transaction(int i, Date date, BigDecimal bigDecimal) {
    }

    // Getters and setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
}
