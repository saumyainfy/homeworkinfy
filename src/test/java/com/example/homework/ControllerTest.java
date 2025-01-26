package com.example.homework;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.homework.controller.RewardController;
import com.example.homework.repository.CustomerRepository;
import com.example.homework.repository.TransactionRepository;
import com.example.homework.service.RewardService;

import com.example.homework.dto.MonthlyRewardsDTO;

@ExtendWith(MockitoExtension.class)
class ControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RewardService rewardService;
    
    private RewardService rewardServiceMock;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private TransactionRepository transactionRepository;
    @InjectMocks
    private RewardController rewardController;

    @BeforeEach
    void setUp() {
    	
        mockMvc = MockMvcBuilders.standaloneSetup(rewardController).build();
        customerRepository =Mockito.mock(CustomerRepository.class);
        transactionRepository=Mockito.mock(TransactionRepository.class);
        rewardServiceMock=Mockito.mock(RewardService.class);
        
        ReflectionTestUtils.setField(rewardController, "rewardService", rewardServiceMock);
        ReflectionTestUtils.setField(rewardService, "customerRepository", customerRepository);
        ReflectionTestUtils.setField(rewardService, "transactionRepository", transactionRepository);
    }

    @Test
    void testGetMonthlyRewards() throws Exception {
        Long customerId = 1L;
        String startDate = "2023-01-01";
        String endDate = "2023-01-31";
        MonthlyRewardsDTO mockRewards = new MonthlyRewardsDTO();
        mockRewards.setCustomerId(customerId);
        mockRewards.setCustomerName("John Doe");

        when(rewardServiceMock.getMonthlyRewards(customerId, startDate, endDate)).thenReturn(mockRewards);
 
        mockMvc.perform(get("/api/rewards/customer/{customerId}/monthly", customerId)
                .param("startDate", startDate)
                .param("endDate", endDate))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(customerId))
                .andExpect(jsonPath("$.customerName").value("John Doe"));

    
    }
    
    
}

