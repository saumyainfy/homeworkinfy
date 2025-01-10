package com.example.homework.service;

import com.example.homework.model.RewardPointsResponse;
import com.example.homework.model.Transaction;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RewardService {

    public BigDecimal calculatePoints(BigDecimal amount) {
        BigDecimal points = BigDecimal.ZERO;
        if (amount.compareTo(new BigDecimal("100")) > 0) {
            BigDecimal above100 = amount.subtract(new BigDecimal("100"));
            points = points.add(above100.multiply(new BigDecimal("2")));
            amount = new BigDecimal("100");
        }
        if (amount.compareTo(new BigDecimal("50")) > 0) {
            BigDecimal between50and100 = amount.subtract(new BigDecimal("50"));
            points = points.add(between50and100.multiply(new BigDecimal("1")));
        }
        return points;
    }

    public List<RewardPointsResponse> calculateMonthlyRewards(List<Transaction> transactions) {
        return transactions.stream()
                .collect(Collectors.groupingBy(
                        transaction -> transaction.getTransactionDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().getMonth().toString(),
                        Collectors.groupingBy(Transaction::getCustomerId, Collectors.reducing(BigDecimal.ZERO, transaction -> calculatePoints(transaction.getTransactionAmount()), BigDecimal::add))
                ))
                .entrySet().stream()
                .flatMap(monthEntry -> monthEntry.getValue().entrySet().stream()
                        .map(entry -> new RewardPointsResponse(entry.getKey(), monthEntry.getKey(), entry.getValue())))
                .collect(Collectors.toList());
    }
}
