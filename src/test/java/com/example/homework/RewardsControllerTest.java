package com.example.homework;

import com.example.homework.controller.RewardController;
import com.example.homework.service.RewardService;
import dto.MonthlyRewardsDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.when;
import org.springframework.test.web.servlet.MockMvc;



@WebMvcTest(RewardController.class)
public class RewardsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RewardService rewardService;

    @Test
    public void testGetRewards_Success() throws Exception {
        Long customerId = 1L;
        String startDate = "2023-01-01";
        String endDate = "2023-01-31";
        MonthlyRewardsDTO mockResponse = new MonthlyRewardsDTO();
        mockResponse.setCustomerId(customerId);
        mockResponse.setCustomerName("Test");

        Map<String, Integer> monthlyRewards = new HashMap<>();
        monthlyRewards.put("2025-01", 115);
        mockResponse.setMonthlyRewards(monthlyRewards);

        when(rewardService.getMonthlyRewards(customerId, startDate, endDate))
                .thenReturn(mockResponse);

    }

}


