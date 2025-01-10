package com.example.homework.model;

import java.math.BigDecimal;


public class RewardPointsResponse {
    private int customerId;
    private String month;
    private BigDecimal totalPoints;

    public RewardPointsResponse(int customerId, String month, BigDecimal totalPoints) {
        this.customerId = customerId;
        this.month = month;
        this.totalPoints = totalPoints;
    }

    // Getters and setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public BigDecimal getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(BigDecimal totalPoints) {
        this.totalPoints = totalPoints;
    }
}
