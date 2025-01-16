package com.example.homework.controller;


import com.example.homework.repository.CustomerRepository;
import com.example.homework.service.RewardService;
import com.example.homework.util.RequestValidator;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    private final RewardService rewardService;

    public RewardController(RewardService rewardService, CustomerRepository customerRepository) {
        this.rewardService = rewardService;
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

        return rewardService.getCustomerRewards(customerId, start, end);

    }

    @GetMapping("/customer/{customerId}/monthly")
    public Map<String, Object> getMonthlyRewards(
            @PathVariable Long customerId,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        // Validate input (delegated to utility)
        RequestValidator.validateCustomerId(customerId);
        RequestValidator.validateAndParseDates(startDate, endDate);
        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
        // Get monthly rewards
        return rewardService.getMonthlyRewards(customerId, start, end);
    }

}
