package com.example.homework.controller;


import com.example.homework.repository.CustomerRepository;
import com.example.homework.service.RewardService;
import com.example.homework.util.RequestValidator;
import dto.MonthlyRewardsDTO;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    private final RewardService rewardService;

    public RewardController(RewardService rewardService, CustomerRepository customerRepository) {
        this.rewardService = rewardService;
    }

    @GetMapping("/customer/{customerId}/monthly")
    public MonthlyRewardsDTO getMonthlyRewards(
            @PathVariable Long customerId,
            @RequestParam String startDate,
            @RequestParam String endDate) {

        // Validate input (delegated to utility)
        RequestValidator.validateCustomerId(customerId);
        RequestValidator.validateAndParseDates(startDate, endDate);

        // Get monthly rewards
        return rewardService.getMonthlyRewards(customerId, startDate, endDate);
    }

}
