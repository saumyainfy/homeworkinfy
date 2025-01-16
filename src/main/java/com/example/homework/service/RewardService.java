package com.example.homework.service;

import com.example.homework.model.Customer;
import com.example.homework.model.Transaction;
import com.example.homework.repository.CustomerRepository;
import com.example.homework.repository.TransactionRepository;
import dto.CustomerRewardsDTO;
import dto.MonthlyRewardsDTO;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
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

    public CustomerRewardsDTO getCustomerRewards(Long customerId, LocalDate startDate, LocalDate endDate) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));


        List<Transaction> transactions = transactionRepository.findAllByCustomerIdAndDateBetween(customerId, startDate, endDate);

        int totalRewards = transactions.stream().mapToInt(t -> calculatePoints(t.getAmount())).sum();

        CustomerRewardsDTO dto = new CustomerRewardsDTO();
        dto.setCustomerId(customer.getId());
        dto.setCustomerName(customer.getName());
        dto.setStartDate(startDate);
        dto.setEndDate(endDate);
        dto.setTotalRewards(totalRewards);
        return dto;

    }
    // New method to calculate monthly rewards
    public MonthlyRewardsDTO getMonthlyRewards(Long customerId, LocalDate startDate, LocalDate endDate) {
        // Check if customer exists
        customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        // Fetch all transactions for the given customer within the date range
        List<Transaction> transactions = transactionRepository.findAllByCustomerIdAndDateBetween(customerId, startDate, endDate);
        // Group transactions by YearMonth and calculate rewards for each group
        Map<String, Integer> monthlyRewards = transactions.stream()
                .collect(Collectors.groupingBy(
                        transaction -> YearMonth.from(transaction.getDate()).toString(), // Convert YearMonth to String
                        Collectors.summingInt(transaction -> calculatePoints(transaction.getAmount())) // Sum up rewards per month
                ));

        // Build the response map
        MonthlyRewardsDTO dto = new MonthlyRewardsDTO();
        dto.setCustomerId(customerId);
        dto.setStartDate(startDate);
        dto.setEndDate(endDate);
        dto.setMonthlyRewards(monthlyRewards);

        return dto;
    }
}