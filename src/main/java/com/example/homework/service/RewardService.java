package com.example.homework.service;

import com.example.homework.model.Transaction;
import com.example.homework.repository.CustomerRepository;
import com.example.homework.repository.TransactionRepository;
import dto.MonthlyRewardsDTO;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RewardService {
    private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;

    public RewardService(TransactionRepository transactionRepository, CustomerRepository customerRepository) {
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
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

    public MonthlyRewardsDTO getMonthlyRewards(Long customerId, String start, String end) {
        // Check if customer exists
        customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        LocalDate startDate = LocalDate.parse(start, DateTimeFormatter.ISO_DATE);
        LocalDate endDate = LocalDate.parse(end, DateTimeFormatter.ISO_DATE);
         String customerName = customerRepository.findById(customerId).get().getName();
        // Fetch all transactions for the given customer within the date range
        List<Transaction> transactions = transactionRepository.findAllByCustomerIdAndDateBetween(customerId, startDate, endDate);

       //filtered transaction
        List<Transaction> filteredTransactions = transactions.stream()
                .filter(transaction -> !transaction.getDate().isBefore(startDate) && !transaction.getDate().isAfter(endDate))
                .collect(Collectors.toList());

        // Group transactions by YearMonth and calculate rewards for each group
        Map<String, Integer> monthlyRewards = transactions.stream()
                .collect(Collectors.groupingBy(
                        transaction -> YearMonth.from(transaction.getDate()).toString(), // Convert YearMonth to String
                        Collectors.summingInt(transaction -> calculatePoints(transaction.getAmount())) // Sum up rewards per month
                ));

        // Build the response map
        MonthlyRewardsDTO dto = new MonthlyRewardsDTO();
        dto.setCustomerId(customerId);
        dto.setCustomerName(customerName);
        dto.setStartDate(startDate);
        dto.setEndDate(endDate);
        dto.setMonthlyRewards(monthlyRewards);
        dto.setTransactions(filteredTransactions);

        return dto;
    }
}