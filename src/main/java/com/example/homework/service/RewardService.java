package com.example.homework.service;

import com.example.homework.model.Transaction;
import com.example.homework.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RewardService {
    private final TransactionRepository transactionRepository;

    public RewardService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public int calculatePoints(double amount) {//$120
        if (amount <= 50) {
            return 0;
        } else if (amount <= 100) {
            return (int) (amount - 50);
        } else {
            return (int) ((amount - 100) * 2 + 50);// (120-100)*2 + 50 =20*2+50=40+50=90
        }
    }

    public int getCustomerRewards(Long customerId, LocalDate startDate, LocalDate endDate) {

        List<Transaction> transactions = transactionRepository.findAllByCustomerIdAndDateBetween(customerId, startDate, endDate);
        return transactions.stream().mapToInt(t -> calculatePoints(t.getAmount())).sum();
    }
    // New method to calculate monthly rewards
    public Map<YearMonth, Integer> getMonthlyRewards(Long customerId, LocalDate startDate, LocalDate endDate) {
        // Fetch all transactions for the given customer within the date range
        List<Transaction> transactions = transactionRepository.findAllByCustomerIdAndDateBetween(customerId, startDate, endDate);

        // Group transactions by YearMonth and calculate rewards for each group
        return transactions.stream()
                .collect(Collectors.groupingBy(
                        transaction -> YearMonth.from(transaction.getDate()), // Group by YearMonth
                        Collectors.summingInt(transaction -> calculatePoints(transaction.getAmount())) // Sum up rewards per month
                ));
    }
}