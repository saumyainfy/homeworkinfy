package com.example.homework;

import com.example.homework.model.Customer;
import com.example.homework.model.Transaction;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelTest {

    @Test
    public void testTransactionConstructorAndGetters() {

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Test");

        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setAmount(200.0);
        transaction.setDate(LocalDate.parse("2025-01-01"));
        transaction.setCustomer(customer);


        assertEquals(1L, transaction.getId());
        assertEquals(200.0, transaction.getAmount());
        assertEquals(LocalDate.parse("2025-01-01"), transaction.getDate());
        assertEquals(customer, transaction.getCustomer());
    }
}
