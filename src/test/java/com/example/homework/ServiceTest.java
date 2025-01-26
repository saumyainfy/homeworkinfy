package com.example.homework;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.homework.model.Customer;
import com.example.homework.repository.CustomerRepository;
import com.example.homework.repository.TransactionRepository;
import com.example.homework.service.RewardService;
import com.example.homework.util.RequestValidator;

import dto.MonthlyRewardsDTO;

@ExtendWith(MockitoExtension.class)
class RewardServiceTest {

	@Mock
	private TransactionRepository transactionRepository;

	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private RewardService rewardService;

	private Long customerId;
	private String startDate;
	private String endDate;

	@BeforeEach
	void setUp() {
		customerId = 1L;
		startDate = "2023-01-01";
		endDate = "2023-01-31";
	}

	@Test
	void testGetMonthlyRewards() {
		Customer mockCustomer = new Customer();
		mockCustomer.setName("John Doe");

		when(customerRepository.findById(customerId)).thenReturn(Optional.of(mockCustomer));
		when(transactionRepository.findAllByCustomerIdAndDateBetween(customerId,
				LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE),
				LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE))).thenReturn(Collections.emptyList());

		MonthlyRewardsDTO result = rewardService.getMonthlyRewards(customerId, startDate, endDate);

		assertEquals(customerId, result.getCustomerId());
		assertEquals("John Doe", result.getCustomerName());
		assertEquals(LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE), result.getStartDate());
		assertEquals(LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE), result.getEndDate());
		assertEquals(Collections.emptyMap(), result.getMonthlyRewards());
		assertEquals(Collections.emptyList(), result.getTransactions());
	}

	@Test
	void testGetMonthlyRewardsCustomerNotFound() {
		when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

		assertThrows(RuntimeException.class, () -> rewardService.getMonthlyRewards(customerId, startDate, endDate));
	
		rewardService.calculatePoints(0);
		rewardService.calculatePoints(100);
		rewardService.calculatePoints(1000);
		
        // Invalid customer ID (null)
        Exception exception = assertThrows(IllegalArgumentException.class, () -> RequestValidator.validateCustomerId(null));
        assertEquals("Invalid customer ID. It must be a positive number.", exception.getMessage());

        // Invalid customer ID (negative)
        exception = assertThrows(IllegalArgumentException.class, () -> RequestValidator.validateCustomerId(-1L));
        assertEquals("Invalid customer ID. It must be a positive number.", exception.getMessage());

        // Invalid customer ID (zero)
        exception = assertThrows(IllegalArgumentException.class, () -> RequestValidator.validateCustomerId(0L));
	    assertEquals("Invalid customer ID. It must be a positive number.", exception.getMessage());
	    
	    
	}
}
