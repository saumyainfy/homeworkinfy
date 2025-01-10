package com.example.homework.controller;


import com.example.homework.model.RewardPointsResponse;
import com.example.homework.model.Transaction;
import com.example.homework.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    // Endpoint to calculate rewards points for customers by month
    @PostMapping("/calculate")
    public List<RewardPointsResponse> calculateRewards(@RequestBody List<Transaction> transactions) {
        return rewardService.calculateMonthlyRewards(transactions);
    }
}
