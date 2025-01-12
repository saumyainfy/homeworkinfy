package com.example.homework.repository;

import com.example.homework.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByCustomerIdAndDateBetween(Long customerId, LocalDate startDate, LocalDate endDate);
}
