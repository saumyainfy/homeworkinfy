package com.example.homework.controller;


import com.example.homework.model.Customer;
import com.example.homework.repository.CustomerRepository;
import com.example.homework.service.RewardService;
import com.example.homework.util.RequestValidator;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    private final RewardService rewardService;
    private final CustomerRepository customerRepository;

    public RewardController(RewardService rewardService, CustomerRepository customerRepository) {
        this.rewardService = rewardService;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customer/{customerId}")
    public Map<String, Object> getCustomerRewards(
            @PathVariable Long customerId,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        //validation logic
        RequestValidator.validateCustomerId(customerId);
        RequestValidator.validateAndParseDates(startDate, endDate);
        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));

        int totalRewards = rewardService.getCustomerRewards(customerId, start, end);

        Map<String, Object> response = new HashMap<>();
        response.put("customerId", customer.getId());
        response.put("customerName", customer.getName());
        response.put("startDate", startDate);
        response.put("endDate", endDate);
        response.put("totalRewards", totalRewards);

        return response;
    }

    @GetMapping("/customer/{customerId}/monthly")
    public Map<YearMonth, Integer> getMonthlyRewards(
            @PathVariable Long customerId,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        // Validate input (delegated to utility)
        RequestValidator.validateCustomerId(customerId);
        RequestValidator.validateAndParseDates(startDate, endDate);
        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);

        // Check if customer exists
        customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Get monthly rewards
        return rewardService.getMonthlyRewards(customerId, start, end);
    }

}
